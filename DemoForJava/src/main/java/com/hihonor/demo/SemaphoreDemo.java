package com.hihonor.demo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("1 is wait");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1 acquire success");
        }).start();
        Thread.sleep(2000);
        semaphore.release();
    }

}
