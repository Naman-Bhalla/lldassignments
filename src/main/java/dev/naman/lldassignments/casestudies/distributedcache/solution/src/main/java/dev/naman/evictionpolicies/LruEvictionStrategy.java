package dev.naman.evictionpolicies;

import dev.naman.CacheEntry;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruEvictionStrategy implements EvictionStrategy {
    private final LinkedHashMap<String, CacheEntry> lruCache;

    public LruEvictionStrategy() {
        this.lruCache = new LinkedHashMap<>();
    }

    @Override
    public void update(CacheEntry entry) {
        lruCache.remove(entry.getKey());
        lruCache.put(entry.getKey(), entry);
    }

    @Override
    public CacheEntry evict(Map<String, CacheEntry> cache) {
        CacheEntry eldest = lruCache.entrySet().iterator().next().getValue();
        cache.remove(eldest.getKey());
        lruCache.remove(eldest.getKey());
        return eldest;
    }
}
