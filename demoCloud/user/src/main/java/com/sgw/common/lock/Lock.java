package com.sgw.common.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁实现
 * @author sunny
 */
public interface Lock {

    void lock();

    boolean tryLock(TimeUnit timeUnit, Long time);

    void unLock();

}
