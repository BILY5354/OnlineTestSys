package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import java.util.List;

public interface ModuleService {

    /**
     * 添加
     * @param module
     * @return
     */
    void save(Module module);

    /**
     * 删除
     * @param module
     * @return
     */
    void delete(Module module);

    /**
     * 修改
     * @param module
     * @return
     */
    void update(Module module);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Module findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Module> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);
}
