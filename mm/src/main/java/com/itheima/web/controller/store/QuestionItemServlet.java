package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(request, response);
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
        String questionId = request.getParameter("questionId");
        //进入list页时添加对应的问题id，为添加操作使用
        request.setAttribute("questionId", questionId);
        //获取数据
        PageInfo all = questionItemService.findAll(questionId, 1, 100);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request, response);
    }


    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionItemService.save(questionItem);
        //跳转回到页面list
        list(request, response);
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");
        QuestionItem questionItem = questionItemService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("questionItem", questionItem);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/update.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionItemService.update(questionItem);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/questionItem?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);
        //调用业务层接口save
        questionItemService.delete(questionItem);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/questionItem?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
