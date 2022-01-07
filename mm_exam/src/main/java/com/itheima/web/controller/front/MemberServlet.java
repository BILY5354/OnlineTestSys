package com.itheima.web.controller.front;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.front.Member;
import com.itheima.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/register")
public class MemberServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.收集数据（request数据）
        String json = JSON.parseObject(request.getInputStream(), String.class);
        System.out.println(json);
        //2.组织成一个实体类（Member）
        Member member = JSON.parseObject(json, Member.class);
        //3.调用逻辑层API
        boolean flag = memberService.register(member);
        //4.返回结果（json数据解析）
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(), "{message:'注册成功'}");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
