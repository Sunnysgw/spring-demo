package com.sgw.common.lock;

/**
 * @description: 锁接口
 * @author: sunnysgw
 * @since: 1.0
 **/
public interface Lock {

    /**
     * 尝试加锁
     * @param worker 加锁的节点名称
     * @return 是否加锁成功
     */
    boolean tryLock(String worker);

    /**
     * 释放锁
     */
    void unLock();

    /**
     * 加锁
     * @param worker 加锁标记
     */
    void lock(String worker);

    /**
     * 没有获取锁，进入阻塞
     */
    void threadWait();
}
