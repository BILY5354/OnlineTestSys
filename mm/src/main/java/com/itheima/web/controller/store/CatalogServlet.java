package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// uri:/store/catalog?operation=list
@WebServlet("/store/catalog")
public class CatalogServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if("list".equals(operation)){
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request, response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            size = Integer.parseInt(request.getParameter("size"));
        }
        PageInfo all = catalogService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //加载学科信息
        List<Course> all = courseService.findAll();
        request.setAttribute("courseList",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Catalog catalog = BeanUtil.fillBean(request,Catalog.class,"yyyy-MM-dd");
        //调用业务层接口save
        catalogService.save(catalog);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath()+"/store/catalog?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");
        Catalog catalog = catalogService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("catalog",catalog);

        //加载学科信息
        List<Course> all = courseService.findAll();
        request.setAttribute("courseList",all);

        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        Catalog catalog = BeanUtil.fillBean(request,Catalog.class,"yyyy-MM-dd");
        //调用业务层接口save
        catalogService.update(catalog);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath()+"/store/catalog?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        Catalog catalog = BeanUtil.fillBean(request,Catalog.class);
        //调用业务层接口save
        catalogService.delete(catalog);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath()+"/store/catalog?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}