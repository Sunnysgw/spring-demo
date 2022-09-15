package com.sgw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgw.dao.DistributeLockDao;
import com.sgw.entity.DistributeLock;
import com.sgw.service.DistributeLockService;
import org.springframework.stereotype.Service;

/**
 * (DistributeLock)表服务实现类
 *
 * @author makejava
 * @since 2022-09-10 10:17:46
 */
@Service("distributeLockService")
public class DistributeLockServiceImpl extends ServiceImpl<DistributeLockDao, DistributeLock> implements DistributeLockService {

}

