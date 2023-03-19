import dev.naman.CacheNode;
import dev.naman.DistributedCache;
import dev.naman.evictionpolicies.EvictionPolicy;
import dev.naman.writepolicies.WritePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class DistributedCacheTest {
    private DistributedCache cache;

    @BeforeEach
    public void setup() {
        int numberOfReplicas = 3;
        int numberOfNodes = 5;
        int cacheCapacity = 1500;
        List<CacheNode> nodes = IntStream.range(0, numberOfNodes)
                .mapToObj(i -> new CacheNode("node" + i, cacheCapacity, WritePolicy.WRITE_THROUGH, EvictionPolicy.LRU, 16))
                .collect(Collectors.toList());

        int prefetchThreshold = 2;
        int prefetchSize = 3;

        cache = new DistributedCache(numberOfReplicas, nodes, prefetchThreshold, prefetchSize);
    }

    @Test
    public void testPutAndGet() throws ExecutionException, InterruptedException {
        cache.putAsync("1", "value1").get();
        Future<String> valueFuture = cache.getAsync("1");
        assertEquals("value1", valueFuture.get());
    }

    @Test
    public void testDelete() throws ExecutionException, InterruptedException {
        cache.putAsync("1", "value1").get();
        Future<Void> deleteFuture = cache.deleteAsync("1");
        deleteFuture.get(); // Wait for the delete operation to complete

        Future<String> valueFuture = cache.getAsync("1");
        assertNull(valueFuture.get());
    }


    @Test
    public void testAddAndRemoveNode() throws ExecutionException, InterruptedException {
        CacheNode newNode = new CacheNode("newNode", 10, WritePolicy.WRITE_THROUGH, EvictionPolicy.LRU, 16);
        cache.addNode(newNode);
        cache.putAsync("2", "value2").get();
        Future<String> valueFuture = cache.getAsync("2");
        assertEquals("value2", valueFuture.get());
        cache.removeNode(newNode);
    }

    @Test
    public void testRequestCollapsing() throws ExecutionException, InterruptedException {
        cache.putAsync("3", "value3").get();

        Future<String> valueFuture1 = cache.getAsync("3");
        Future<String> valueFuture2 = cache.getAsync("3");

        assertEquals("value3", valueFuture1.get());
        assertEquals("value3", valueFuture2.get());
    }

    // Implement this test method based on the logic of the `prefetchKeys` method
    @Test
    public void testPrefetching() throws ExecutionException, InterruptedException {
        cache.putAsync("1", "value1");
        cache.putAsync("2", "value2");
        cache.putAsync("3", "value3");

        // Access key "1", which should trigger prefetching for keys "2" and "3"
        Future<String> valueFuture1 = cache.getAsync("1");
        assertEquals("value1", valueFuture1.get());

        // Check if keys "2" and "3" have been prefetched by comparing their associated futures
        Future<String> valueFuture2 = cache.getAsync("2");
        Future<String> valueFuture3 = cache.getAsync("3");
        assertNotEquals(valueFuture2, valueFuture3);
    }



    @Test
    public void testConcurrentPutAndGet() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> putFutures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String key = String.valueOf(i);
            String value = "value" + i;
            cache.putAsync(key, value);
            putFutures.add(cache.putAsync(key, value));
        }

//         Wait for all put operations to complete
        CompletableFuture.allOf(putFutures.toArray(new CompletableFuture[0])).get();

        List<CompletableFuture<String>> getFutures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String key = String.valueOf(i);
            getFutures.add((CompletableFuture<String>) cache.getAsync(key));
        }

        List<String> results = getFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        for (int i = 0; i < 1000; i++) {
            assertEquals("value" + i, results.get(i));
        }
    }

}
