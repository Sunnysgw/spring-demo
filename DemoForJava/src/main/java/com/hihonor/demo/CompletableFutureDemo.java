package com.hihonor.demo;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureDemo {

    private static final ExecutorService es = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture
                .supplyAsync(() -> new Random().nextInt(100))
                .thenApply(item -> item > 50 ? item + 100 : item - 100)

    }

}
