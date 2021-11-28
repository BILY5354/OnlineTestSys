package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionItemDao;
import com.itheima.domain.store.QuestionItem;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionItemService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    @Override
    public void save(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            questionItem.setId(id);
            //3.调用Dao层操作
            questionItemDao.save(questionItem);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //3.调用Dao层操作
            questionItemDao.delete(questionItem);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //3.调用Dao层操作
            questionItemDao.update(questionItem);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public QuestionItem findById(String id) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //3.调用Dao层操作
            return questionItemDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<QuestionItem> findAll() {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //3.调用Dao层操作
            return questionItemDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
            //3.调用Dao层操作
            PageHelper.startPage(page,size);
            List<QuestionItem> all = questionItemDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}