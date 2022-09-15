package com.sgw.provider.api;

import com.sgw.provider.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * (UserInfo)表控制层
 *
 * @author makejava
 * @since 2022-09-05 15:02:24
 */
public interface UserInfoControllerAPI {

    /**
     * 分页查询
     *
     * @param userInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //@GetMapping
    //ResponseEntity<Page<UserInfo>> queryByPage(UserInfo userInfo, PageRequest pageRequest);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserInfo queryById(@PathVariable("id") Integer id);

    /**
     * 新增数据
     *
     * @param userInfo 实体
     * @return 新增结果
     */
    @PostMapping
    ResponseEntity<UserInfo> add(UserInfo userInfo);

    /**
     * 编辑数据
     *
     * @param userInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    ResponseEntity<UserInfo> edit(UserInfo userInfo);

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    ResponseEntity<Boolean> deleteById(Integer id);

}

