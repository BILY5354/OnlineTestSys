package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemService {

    /**
     * 添加
     * @param questionItem
     * @return
     */
    void save(QuestionItem questionItem);

    /**
     * 删除
     * @param questionItem
     * @return
     */
    void delete(QuestionItem questionItem);

    /**
     * 修改
     * @param questionItem
     * @return
     */
    void update(QuestionItem questionItem);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    QuestionItem findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<QuestionItem> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);
}
