package com.itheima.dao.front;

import com.itheima.domain.front.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    int save(Member member);

    //Member findByEmailAndPwd(@Param("email") String email,@Param("password") String password);
}
