package com.sgw;

import com.sgw.common.CuratorBaseOperation;
import com.sgw.common.ExecutorsFactory;
import com.sgw.common.lock.AbstractLock;
import com.sgw.common.lock.Lock;
import com.sgw.common.lock.ZookeeperLock;
import lombok.extern.java.Log;
import org.apache.curator.framework.CuratorFramework;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.sgw.dao")
@Log
public class App extends CuratorBaseOperation {

    private static Integer count = 0;

    public static void main( String[] args ) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        CuratorFramework curatorFramework = run.getBean(CuratorFramework.class);
        ThreadPoolExecutor threadPoolExecutor = ExecutorsFactory.getThreadPoolExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                Lock lock = new ZookeeperLock(curatorFramework);
                lock.lock(Thread.currentThread().getName());
                try {
                    for (int j = 0; j < 1000; j++) {
                        count++;
                    }
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ignore) {
                } finally {
                    countDownLatch.countDown();
                    lock.unLock();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException ignore) {
        }
        log.info("final count:" + count);
    }
}
