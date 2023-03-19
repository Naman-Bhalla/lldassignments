package dev.naman;

import java.util.concurrent.Future;

public class CacheClient {
    private final DistributedCache distributedCache;

    public CacheClient(DistributedCache distributedCache) {
        this.distributedCache = distributedCache;
    }

    public Future<Void> putAsync(String key, String value) {
        return distributedCache.putAsync(key, value);
    }

    public Future<String> getAsync(String key) {
        return distributedCache.getAsync(key);
    }

    public Future<Void>  deleteAsync(String key) {
        return distributedCache.deleteAsync(key);
    }
}
