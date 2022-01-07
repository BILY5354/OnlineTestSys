package com.itheima.web.controller;

import com.itheima.service.front.MemberService;
import com.itheima.service.front.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {

    protected MemberService memberService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImpl();
    }
}
