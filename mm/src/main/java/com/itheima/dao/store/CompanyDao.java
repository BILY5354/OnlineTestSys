package com.itheima.dao.store;

import com.itheima.domain.store.Company;

import java.util.List;


/***/
public interface CompanyDao {

    int save(Company company);

    int delete(Company company);

    int update(Company company);

    Company findById(String id);

    List<Company> findAll();
}
