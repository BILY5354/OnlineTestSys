package com.itheima.dao.system;


import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    int save(Dept dept);

    int delete(Dept dept);

    int update(Dept dept);

    Dept findById(String id);

    List<Dept> findAll();
}
