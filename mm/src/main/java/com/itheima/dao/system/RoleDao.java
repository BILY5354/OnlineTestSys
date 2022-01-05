package com.itheima.dao.system;


import com.itheima.domain.system.Role;

import java.util.List;

public interface RoleDao {

    int save(Role role);

    int delete(Role role);

    int update(Role role);

    Role findById(String id);

    List<Role> findAll();
}
