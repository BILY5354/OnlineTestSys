package com.itheima.web.controller.system;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// uri:/system/dept?operation=list
@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(request, response);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(request, response);
        } else if ("save".equals(operation)) {
            this.save(request, response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        } else if ("edit".equals(operation)) {
            this.edit(request, response);
        } else if ("delete".equals(operation)) {
            this.delete(request, response);
        }

    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        PageInfo all = deptService.findAll(page, size);
        //将数据保存到指定位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(request, response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载所有的部门信息放入到deptList
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(request, Dept.class, "yyyy-MM-dd");
        //调用业务层接口save
        deptService.save(dept);
        //跳转回到页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    //进入修改页面准备改了
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");
        Dept dept = deptService.findById(id);
        //加载所有的部门信息放入到deptList
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("dept", dept);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(request, response);
    }

    //重新填好表单 保存返回企业管理页面
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(request, Dept.class, "yyyy-MM-dd");
        //调用业务层接口save
        deptService.update(dept);
        //跳转回到页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(request, Dept.class);
        //调用业务层接口save
        deptService.delete(dept);
        //跳转回到页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
