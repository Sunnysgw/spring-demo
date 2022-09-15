package com.sgw.common.lock;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @description: 基于zk实现的分布式锁
 * @author: sunnysgw
 * @since: 1.0
 **/
public class ZookeeperLock extends AbstractLock {

    private static final String PREFIX = "/distribute";

    private static final String LOCK = "/lock";

    private static final String SPLIT = "/";

    private final Object lock = new Object();

    private String path  = null;

    private final CuratorFramework curatorFramework;

    public ZookeeperLock(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public boolean tryLock(String worker) {
        try {
            if (StringUtils.isBlank(path)) {
                path = curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .forPath(PREFIX + LOCK, worker.getBytes(StandardCharsets.UTF_8));
                path = path.substring(path.indexOf(LOCK) + 1);
            }
            List<String> pathList = curatorFramework.getChildren().forPath(PREFIX);
            if (checkLock(pathList, path)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean checkLock(List<String> pathList, String path) {
        Collections.sort(pathList);
        if (pathList.get(0).endsWith(path)) {
            return true;
        }
        int i = pathList.indexOf(path);
        try {
            curatorFramework.getData()
                    .usingWatcher(new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if (Event.EventType.NodeDeleted.equals(event.getType())) {
                                synchronized (lock) {
                                    lock.notifyAll();
                                }
                            }
                        }
                    }).forPath(PREFIX + SPLIT + pathList.get(i - 1));
        } catch (Exception ignore) {
        }
        return false;
    }

    @Override
    public void unLock() {
        try {
            curatorFramework.delete().forPath(PREFIX + SPLIT + path);
        } catch (Exception ignore) {
        }
    }

    @Override
    public void threadWait() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException ignore) {
            }
        }
    }
}
