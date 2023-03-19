package dev.naman.evictionpolicies;

import dev.naman.CacheEntry;

import java.util.Map;

public interface EvictionStrategy {
    void update(CacheEntry entry);
    CacheEntry evict(Map<String, CacheEntry> cache);
}
