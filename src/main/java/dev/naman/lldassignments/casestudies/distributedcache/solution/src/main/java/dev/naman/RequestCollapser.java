package dev.naman;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

public class RequestCollapser {
    private final ConcurrentMap<String, Future<String>> requestMap;

    public RequestCollapser() {
        this.requestMap = new ConcurrentHashMap<>();
    }

    public Future<String> collapse(String key, Future<String> newRequest) {
        Future<String> existingRequest = requestMap.putIfAbsent(key, newRequest);
        return existingRequest != null ? existingRequest : newRequest;
    }

    public void remove(String key) {
        requestMap.remove(key);
    }
}
