package com.sgw.common.lock;

import com.sgw.entity.DistributeLock;
import com.sgw.service.DistributeLockService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description: 基于mysql的分布式lock实现
 * @author: sunnysgw
 * @since: 1.0
 **/
@Component
public class SqlLock extends AbstractLock {

    private static final Integer LOCK_FLAG = 1;

    @Resource
    private DistributeLockService lockService;

    @Override
    public boolean tryLock(String worker) {
        DistributeLock lock = new DistributeLock();
        lock.setLockFlag(LOCK_FLAG);
        lock.setWorkerName(worker);
        try {
            return lockService.save(lock);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unLock() {
        lockService.lambdaUpdate().eq(DistributeLock::getLockFlag, LOCK_FLAG).remove();
    }

    @Override
    public void threadWait() {
        sleep(TimeUnit.MILLISECONDS, 10);
    }

    /**
     * 阻塞指定时间
     * @param timeUnit 单位
     * @param sleepTime 时间长度
     */
    public void sleep(TimeUnit timeUnit, long sleepTime) {
        try {
            timeUnit.sleep(sleepTime);
        } catch (InterruptedException ignore) {
        }
    }
}
