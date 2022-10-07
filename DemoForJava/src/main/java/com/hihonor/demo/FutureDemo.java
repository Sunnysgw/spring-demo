package com.hihonor.demo;

import javafx.concurrent.Task;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static class WorkTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sleepSecond = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(sleepSecond);
            return sleepSecond;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> future1 = new FutureTask<>(new WorkTask());
        FutureTask<Integer> future2 = new FutureTask<>(new WorkTask());
        FutureTask<Integer> future3 = new FutureTask<>(new WorkTask());
        FutureTask<Integer> future4 = new FutureTask<>(new WorkTask());
        FutureTask<Integer> future5 = new FutureTask<>(new WorkTask());
        executorService.execute(future1);
        executorService.execute(future2);
        executorService.execute(future3);
        executorService.execute(future4);
        executorService.execute(future5);
        executorService.execute(() -> {
            try {
                System.out.println(future1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                System.out.println(future1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
