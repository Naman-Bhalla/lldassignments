package dev.naman.evictionpolicies;

public class EvictionStrategyFactory {
    public static EvictionStrategy createEvictionStrategy(EvictionPolicy evictionPolicy) {
        switch (evictionPolicy) {
            case LRU:
                return new LruEvictionStrategy();
            case LFU:
                return new LfuEvictionStrategy();
            default:
                throw new IllegalArgumentException("Invalid eviction policy: " + evictionPolicy);
        }
    }
}
