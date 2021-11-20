package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;

import java.util.List;

public interface CatalogService {
    /**
     * 添加
     * @param catalog
     * @return
     */
    void save(Catalog catalog);

    /**
     * 删除
     * @param catalog
     * @return
     */
    void delete(Catalog catalog);

    /**
     * 修改
     * @param catalog
     * @return
     */
    void update(Catalog catalog);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Catalog findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Catalog> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);
}
