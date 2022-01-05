package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;

import java.util.List;

public interface RoleService {

    /**
     * 添加
     * @param role
     * @return
     */
    void save(Role role);

    /**
     * 删除
     * @param role
     * @return
     */
    void delete(Role role);

    /**
     * 修改
     * @param role
     * @return
     */
    void update(Role role);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Role findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Role> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     * 建立角色与模块之间的关联
     * @param roleId 角色id
     * @param moduleIds 模块id（多个）
     */
    void updateRoleModule(String roleId, String moduleIds);
}
