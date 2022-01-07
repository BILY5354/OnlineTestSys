package com.itheima.web.controller.front;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.front.Member;
import com.itheima.web.controller.BaseServlet;
import com.itheima.web.controller.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {

    public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        boolean flag = memberService.register(member);
        return new Result("注册成功！", null);
    }

    /*public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        member = memberService.login(member.getEmail(),member.getPassword());

        if(member != null){
            return new Result("登录成功！", member);
        }else{
            return new Result("用户名密码错误，请重试！", false, null, Code.LOGIN_FAIL);
        }
    }*/

}





   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.收集数据（request数据）
        String json = JSON.parseObject(request.getInputStream(), String.class);
        System.out.println(json);
        //2.组织成一个实体类（Member）
        Member member = JSON.parseObject(json, Member.class);
        //3.调用逻辑层API
        boolean flag = memberService.register(member);
        //4.返回的数据封装成一个对象
        Result result = new Result("注册成功！", null);
        //5.返回结果（json数据解析）
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(), result);
    }*/
