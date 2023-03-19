package dev.naman;

import dev.naman.evictionpolicies.EvictionPolicy;
import dev.naman.evictionpolicies.EvictionStrategy;
import dev.naman.evictionpolicies.LfuEvictionStrategy;
import dev.naman.evictionpolicies.LruEvictionStrategy;
import dev.naman.writepolicies.WritePolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


public class CacheNode {
    private final String nodeId;
    private final int capacity;
    private final WritePolicy writePolicy;
    private final EvictionStrategy evictionStrategy;
    private final Map<String, CacheEntry> cache;
    private final StripedExecutorService stripedExecutorService;


    public CacheNode(String nodeId, int capacity, WritePolicy writePolicy, EvictionPolicy evictionPolicy, int numStripes) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.writePolicy = writePolicy;
        this.evictionStrategy = createEvictionStrategy(evictionPolicy);
        this.cache = new HashMap<>(capacity);
        this.stripedExecutorService = new StripedExecutorService(numStripes);
    }

    public String getNodeId() {
        return nodeId;
    }

    public synchronized void put(String key, String value) {
        if (cache.size() >= capacity) {
            CacheEntry evicted = evictionStrategy.evict(cache);
            System.out.println("Evicted: " + evicted.getKey() + " from node " + nodeId);
        }

        CacheEntry entry = new CacheEntry(key, value);
        cache.put(key, entry);
        evictionStrategy.update(entry);

        if (writePolicy == WritePolicy.WRITE_THROUGH) {
            writeToBackendStore(key, value);
        }
    }

    public synchronized String get(String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            String value = readFromBackendStore(key);
            if (value != null) {
                put(key, value);
                return value;
            }
            return null;
        }

        evictionStrategy.update(entry);

        if (writePolicy == WritePolicy.WRITE_BACK) {
            writeToBackendStore(key, entry.getValue());
        }

        return entry.getValue();
    }

    public synchronized void delete(String key) {
        cache.remove(key);
        deleteFromBackendStore(key);
    }

    private EvictionStrategy createEvictionStrategy(EvictionPolicy evictionPolicy) {
        switch (evictionPolicy) {
            case LRU:
                return new LruEvictionStrategy();
            case LFU:
                return new LfuEvictionStrategy();
            default:
                throw new IllegalArgumentException("Invalid eviction policy: " + evictionPolicy);
        }
    }

    // These methods should be implemented to interact with the backend store
    private void writeToBackendStore(String key, String value) {
        // Write key-value to backend store
    }

    private String readFromBackendStore(String key) {
        // Read value from backend store for the given key
        return null;
    }

    private void deleteFromBackendStore(String key) {
        // Delete key from backend store
    }

    public CompletableFuture<String> getAsync(String key) {
        return stripedExecutorService.submit(key, () -> get(key));
    }

    public CompletableFuture<Void> putAsync(String key, String value) {
        return stripedExecutorService.submit(key, () -> {
            put(key, value);
            return null;
        });
    }

    public CompletableFuture<Void> deleteAsync(String key) {
        return stripedExecutorService.submit(key, () -> {
            delete(key);
            return null;
        });
    }
}

