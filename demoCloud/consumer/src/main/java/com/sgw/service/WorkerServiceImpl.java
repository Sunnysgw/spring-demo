package com.sgw.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sgw.common.constants.SentinelConstants;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
@Service
@Log
public class WorkerServiceImpl implements IWorkerService{
    @Override
    @SentinelResource(value = SentinelConstants.WORK_RESOURCE, blockHandler = "blockHandler")
    public void work() {
        log.info("work");
    }

    public static void blockHandler(BlockException blockException) throws BlockException {
        log.info("降级了");
        throw blockException;
    }

}
