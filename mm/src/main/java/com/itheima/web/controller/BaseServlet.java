package com.itheima.web.controller;

import com.itheima.service.store.CompanyService;
import com.itheima.service.store.impl.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected CompanyService companyService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
    }
}
