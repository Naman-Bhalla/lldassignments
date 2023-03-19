package dev.naman.evictionpolicies;

import dev.naman.CacheEntry;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LfuEvictionStrategy implements EvictionStrategy {
    private final Map<String, Integer> frequencyMap;
    private final PriorityQueue<CacheEntry> lfuCache;

    public LfuEvictionStrategy() {
        this.frequencyMap = new HashMap<>();
        this.lfuCache = new PriorityQueue<>((e1, e2) -> frequencyMap.get(e1.getKey()) - frequencyMap.get(e2.getKey()));
    }

    @Override
    public void update(CacheEntry entry) {
        frequencyMap.put(entry.getKey(), frequencyMap.getOrDefault(entry.getKey(), 0) + 1);
        lfuCache.remove(entry);
        lfuCache.offer(entry);
    }

    @Override
    public CacheEntry evict(Map<String, CacheEntry> cache) {
        CacheEntry leastFrequent = lfuCache.poll();
        cache.remove(leastFrequent.getKey());
        frequencyMap.remove(leastFrequent.getKey());
        return leastFrequent;
    }
}
