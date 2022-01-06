package com.itheima.dao.system;

import com.itheima.domain.system.Module;

import java.util.List;
import java.util.Map;

public interface ModuleDao {

    int save(Module module);

    int delete(Module module);

    int update(Module module);

    Module findById(String id);

    List<Module> findAll();

    List<Map> findAuthorDataByRoleId(String roleId);

    List<Module> findModuleByUserId(String id);
}
