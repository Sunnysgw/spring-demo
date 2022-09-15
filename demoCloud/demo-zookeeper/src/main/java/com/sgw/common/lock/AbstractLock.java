package com.sgw.common.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author sunnys
 */
public abstract class AbstractLock implements Lock{

    /**
     * 加锁
     * @param worker 加锁的节点名称
     */
    @Override
    public void lock(String worker) {
        if (!tryLock(worker)) {
            for (;;) {
                threadWait();
                if (tryLock(worker)) {
                    return;
                }
            }
        }
    }

}
