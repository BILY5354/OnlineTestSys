package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;

import java.util.List;

public interface CompanyService {

    /**
     * 添加
     * @param company
     * @return
     */
    void save(Company company);

    /**
     * 删除
     * @param company
     * @return
     */
    void delete(Company company);

    /**
     * 修改
     * @param company
     * @return
     */
    void update(Company company);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Company findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Company> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);
}
