package com.sgw.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgw.entity.DistributeLock;
import com.sgw.service.DistributeLockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (DistributeLock)表控制层
 *
 * @author makejava
 * @since 2022-09-10 10:17:42
 */
@RestController
@RequestMapping("distributeLock")
public class DistributeLockController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DistributeLockService distributeLockService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param distributeLock 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<DistributeLock> page, DistributeLock distributeLock) {
        return success(this.distributeLockService.page(page, new QueryWrapper<>(distributeLock)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.distributeLockService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param distributeLock 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody DistributeLock distributeLock) {
        return success(this.distributeLockService.save(distributeLock));
    }

    /**
     * 修改数据
     *
     * @param distributeLock 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody DistributeLock distributeLock) {
        return success(this.distributeLockService.updateById(distributeLock));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.distributeLockService.removeByIds(idList));
    }
}

