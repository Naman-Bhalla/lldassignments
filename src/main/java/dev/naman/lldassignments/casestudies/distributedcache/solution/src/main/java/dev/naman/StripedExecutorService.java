package dev.naman;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class StripedExecutorService extends AbstractExecutorService {
    private final ExecutorService[] executors;
    private final int numStripes;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public StripedExecutorService(int numStripes) {
        this.numStripes = numStripes;
        this.executors = new ExecutorService[numStripes];
        for (int i = 0; i < numStripes; i++) {
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    @Override
    public void shutdown() {
        if (isShutdown.compareAndSet(false, true)) {
            for (ExecutorService executor : executors) {
                executor.shutdown();
            }
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("shutdownNow is not supported by StripedExecutorService.");
    }

    @Override
    public boolean isShutdown() {
        return isShutdown.get();
    }

    @Override
    public boolean isTerminated() {
        boolean terminated = true;
        for (ExecutorService executor : executors) {
            terminated &= executor.isTerminated();
        }
        return terminated;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long timeoutMillis = unit.toMillis(timeout) / numStripes;
        boolean terminated = true;
        for (ExecutorService executor : executors) {
            terminated &= executor.awaitTermination(timeoutMillis, TimeUnit.MILLISECONDS);
        }
        return terminated;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        throw new UnsupportedOperationException("Direct task submission is not supported by StripedExecutorService. Use submit(String, Callable) instead.");
    }

    public <T> CompletableFuture<T> submit(String key, Callable<T> task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("StripedExecutorService is shut down.");
        }
        int stripe = Math.abs(key.hashCode()) % numStripes;
        CompletableFuture<T> result = new CompletableFuture<>();
        executors[stripe].submit(() -> {
            try {
                result.complete(task.call());
            } catch (Exception e) {
                result.completeExceptionally(e);
            }
        });
        return result;
    }

    @Override
    public void execute(Runnable command) {
        throw new UnsupportedOperationException("Direct task execution is not supported by StripedExecutorService. Use submit(String, Callable) instead.");
    }
}
