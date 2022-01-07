package com.itheima.service.front.impl;

import com.itheima.dao.front.MemberDao;
import com.itheima.domain.front.Member;
import com.itheima.factory.MapperFactory;
import com.itheima.service.front.MemberService;
import com.itheima.utils.MD5Util;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.UUID;

public class MemberServiceImpl implements MemberService {
    @Override
    public boolean register(Member member) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            member.setId(id);
            member.setRegisterDate(new Date());
            member.setState("1");
            member.setPassword(MD5Util.md5(member.getPassword()));
            /*member.setPassword(MD5Util.md5(member.getPassword()));*/
            //3.调用Dao层操作
            int row = memberDao.save(member);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
            return row > 0;
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
