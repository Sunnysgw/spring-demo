package com.sgw.common;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunnys
 */
public class ExecutorsFactory {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024), new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("com.sgw.executors-" + ATOMIC_INTEGER.getAndIncrement());
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return THREAD_POOL_EXECUTOR;
    }

}
