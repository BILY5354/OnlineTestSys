package com.itheima.dao.store;

import com.itheima.domain.store.Question;

import java.util.List;

public interface QuestionDao {

    int save(Question question);

    int delete(Question question);

    int update(Question question);

    Question findById(String id);

    List<Question> findAll();
}
