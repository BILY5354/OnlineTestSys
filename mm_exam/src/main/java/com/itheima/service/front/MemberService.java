package com.itheima.service.front;

import com.itheima.domain.front.Member;

public interface MemberService {

    /**
     * 注册新用户
     * @param member
     * @return
     */
    boolean register(Member member);
}
