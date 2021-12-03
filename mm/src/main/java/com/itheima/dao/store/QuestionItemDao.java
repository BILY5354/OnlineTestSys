package com.itheima.dao.store;

import com.itheima.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
    int save(QuestionItem questionItem);

    int delete(QuestionItem questionItem);

    int update(QuestionItem questionItem);

    QuestionItem findById(String id);

    /**
     * 分页查询数据
     * @param questionId 题目对应的id
     * @return
     */
    List<QuestionItem> findAll(String questionId);
}