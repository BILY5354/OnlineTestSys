package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// uri:/system/user?operation=list
@WebServlet("/system/user")
public class UserServlet extends BaseServlet {

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
        } else if ("userRoleList".equals(operation)) {
            this.userRoleList(request, response);
        } else if ("updateRole".equals(operation)) {
            this.updateRole(request, response);
        } else if ("login".equals(operation)) {
            this.login(request, response);
        } else if ("logout".equals(operation)) {
            this.logout(request, response);
        } else if ("home".equals(operation)) {
            this.home(request, response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //进入列表页
        //获取数据
//        UserService userService = new UserServiceImpl();
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        PageInfo all = userService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request, response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询部门信息
        List<Dept> all = deptService.findAll();
        //放入指定位置
        request.setAttribute("deptList", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        //调用业务层接口save
        userService.save(user);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");
        User user = userService.findById(id);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("user", user);
        //查询部门信息
        List<Dept> all = deptService.findAll();
        //放入指定位置
        request.setAttribute("deptList", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        //调用业务层接口save
        userService.update(user);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(request, User.class);
        //调用业务层接口save
        userService.delete(user);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = userService.findById(userId);
        //将数据加载到指定区域，供页面获取
        request.setAttribute("user", user);
        //获取所有的角色列表
        List<Role> all = roleService.findAllRoleByUserId(userId);
        request.setAttribute("roleList", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request, response);
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String[] roleIds = request.getParameterValues("roleIds");
        userService.updateRole(userId, roleIds);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        User user = userService.login(email, pwd);//判断密码
        if (user != null) {
            request.getSession().setAttribute("loginUser", user);
            //如果登录成功，加载该用户对应的角色对应的所有模块
            List<Module> moduleList = userService.findModuleById(user.getId());
            request.setAttribute("moduleList",moduleList);

            //当前登录用户对应的可操作模块的所有url拼接成一个大的字符串
            StringBuffer sbf = new StringBuffer();
            for(Module m: moduleList){
                sbf.append(m.getCurl());
                sbf.append(',');
            }
            request.getSession().setAttribute("authorStr",sbf.toString());


            //跳转页面
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }


    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}