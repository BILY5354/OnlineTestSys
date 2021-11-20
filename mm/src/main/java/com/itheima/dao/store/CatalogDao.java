package com.itheima.dao.store;

import com.itheima.domain.store.Catalog;

import java.util.List;

public interface CatalogDao {
    int save(Catalog catalog);

    int delete(Catalog catalog);

    int update(Catalog catalog);

    Catalog findById(String id);

    List<Catalog> findAll();
}
