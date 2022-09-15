package com.sgw.common;

import lombok.extern.java.Log;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * curator的基础操作，包括构建连接客户端
 * @author sunnys
 */
@Log
public class CuratorBaseOperation {

    private static volatile CuratorFramework curatorFramework = null;

    private final static String CONNECT_STRING = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private static final Object LOCK = new Object();

    private static void init() {
        if (curatorFramework == null) {
            synchronized (LOCK) {
                if (curatorFramework == null) {
                    curatorFramework = CuratorFrameworkFactory.builder()
                            // 连接session的超时时间
                            .sessionTimeoutMs(4000)
                            .connectString(CONNECT_STRING)
                            .retryPolicy(new ExponentialBackoffRetry(3000, 3))
                            // 对应节点的统一前缀，用以区分不同的环境
                            .namespace("")
                            .build();
                    curatorFramework.getConnectionStateListenable().addListener((client, newState) -> {
                        if (newState.isConnected()) {
                            log.info("connect successfully");
                        }
                    });
                    log.info("begin connect");
                    curatorFramework.start();
                }
            }
        }
    }

    public static CuratorFramework curatorFramework() {
        init();
        return curatorFramework;
    }



}
