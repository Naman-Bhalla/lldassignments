package dev.naman;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final int numberOfReplicas;
    private final SortedMap<Integer, CacheNode> circle = new TreeMap<>();

    public ConsistentHashing(int numberOfReplicas, Collection<CacheNode> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (CacheNode node : nodes) {
            add(node);
        }
    }

    public synchronized void add(CacheNode node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put((node.getNodeId() + i).hashCode(), node);
        }
    }

    public synchronized void remove(CacheNode node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove((node.getNodeId() + i).hashCode());
        }
    }

    public CacheNode get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, CacheNode> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
