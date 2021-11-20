package com.itheima.web.controller;

import com.itheima.service.store.CatalogService;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.CourseService;
import com.itheima.service.store.impl.CatalogServiceImpl;
import com.itheima.service.store.impl.CompanyServiceImpl;
import com.itheima.service.store.impl.CourseServiceImpl;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.UserService;
import com.itheima.service.system.impl.DeptServiceImpl;
import com.itheima.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {

    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
    }

}
