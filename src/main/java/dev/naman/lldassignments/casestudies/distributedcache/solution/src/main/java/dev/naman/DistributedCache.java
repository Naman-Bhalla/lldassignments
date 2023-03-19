package dev.naman;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class DistributedCache {
    private final ConsistentHashing consistentHashing;
    private final RequestCollapser requestCollapser;
    private final int prefetchThreshold;
    private final int prefetchSize;

    public DistributedCache(int numberOfReplicas, List<CacheNode> nodes, int prefetchThreshold, int prefetchSize) {
        this.consistentHashing = new ConsistentHashing(numberOfReplicas, nodes);
        this.requestCollapser = new RequestCollapser();
        this.prefetchThreshold = prefetchThreshold;
        this.prefetchSize = prefetchSize;
    }

    public CompletableFuture<Void> putAsync(String key, String value) {
        CacheNode node = consistentHashing.get(key);
        if (node != null) {
            return node.putAsync(key, value);
        }

        return CompletableFuture.completedFuture(null);
    }

    public Future<String> getAsync(String key) {
        CacheNode node = consistentHashing.get(key);
        if (node != null) {
            Future<String> futureValue = node.getAsync(key);
            requestCollapser.remove(key);

            Future<String> collapsedFuture = requestCollapser.collapse(key, futureValue);
            prefetch(key);
            return collapsedFuture;
        }
        return CompletableFuture.completedFuture(null);
    }

    public Future<Void> deleteAsync(String key) {
        CacheNode node = consistentHashing.get(key);
        if (node != null) {
            return node.deleteAsync(key);
        }
        return CompletableFuture.completedFuture(null);
    }

    public void addNode(CacheNode node) {
        consistentHashing.add(node);
    }

    public void removeNode(CacheNode node) {
        consistentHashing.remove(node);
    }

    // Implement your own logic for prefetching the keys
    private List<String> prefetchKeys(String key) {
        int currentKey = Integer.parseInt(key);
        return List.of(String.valueOf(currentKey + 1), String.valueOf(currentKey + 2));
    }


    private void prefetch(String key) {
        if (prefetchThreshold <= 0 || prefetchSize <= 0) {
            return;
        }

        List<String> keysToPrefetch = prefetchKeys(key);

        for (int i = 0; i < prefetchSize && i < keysToPrefetch.size(); i++) {
            String prefetchKey = keysToPrefetch.get(i);
            CacheNode node = consistentHashing.get(prefetchKey);
            if (node != null) {
                // Prefetch the key asynchronously
                node.getAsync(prefetchKey);
            }
        }
    }
}
