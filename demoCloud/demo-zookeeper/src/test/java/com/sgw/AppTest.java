package com.sgw;

import static org.junit.Assert.assertTrue;

import com.sgw.common.ExecutorsFactory;
import com.sgw.common.lock.SqlLock;
import lombok.extern.java.Log;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Unit test for simple App.
 */
@Log
@SpringBootTest(classes = App.class)
public class AppTest
{
    private static final String CONNECT_STR = "localhost:2181";

    private int count  = 0;

    @Resource
    private SqlLock lock;

    @Test
    public void testMutiThreadInsert() {
        ThreadPoolExecutor threadPoolExecutor = ExecutorsFactory.getThreadPoolExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                lock.lock(Thread.currentThread().getName());
                try {
                    for (int j = 0; j < 1000; j++) {
                        count++;
                        Thread.yield();
                    }
                    countDownLatch.countDown();
                } finally {
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


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STR, 1000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected.equals(event.getState()) && Event.EventType.None.equals(event.getType())) {
                    countDownLatch.countDown();
                }
                log.info("connect success");
            }
        });
        log.info("connecting");
        countDownLatch.await();
        log.info(String.valueOf(zooKeeper.getState()));
    }
}
