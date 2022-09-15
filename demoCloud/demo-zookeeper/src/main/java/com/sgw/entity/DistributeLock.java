package com.sgw.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (DistributeLock)表实体类
 *
 * @author makejava
 * @since 2022-09-10 10:17:45
 */
@SuppressWarnings("serial")
public class DistributeLock extends Model<DistributeLock> {
    
    private Integer lockFlag;
    
    private String workerName;


    public Integer getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Integer lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.lockFlag;
    }
    }

