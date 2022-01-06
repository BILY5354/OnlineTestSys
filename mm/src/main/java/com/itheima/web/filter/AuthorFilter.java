package com.itheima.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class AuthorFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.定义和协议相关的请求和响应对象
        HttpServletRequest request ;
        HttpServletResponse response;
        HttpSession session;
        try{
            //2.把参数转换成协议相关的对象
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
            session = request.getSession();
            //1.获取本次操作
            String url = request.getRequestURI();
            //.css   .js    .png   .jpg   .index (放行)
            if(url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith("index.jsp")
                    || url.endsWith("unauthorized.jsp")
                    || url.endsWith("login.jsp")){
                chain.doFilter(request,response);
                return;
            }
            String queryString = request.getQueryString();
            if(queryString.endsWith("operation=login")){
                chain.doFilter(request,response);
                return;
            }


            //1.当前获取到的url：   /system/dept
            url = url.substring(1);
            //2.当前获取到的查询参数：operation=list       operation=toEdit&id=100
            int index = queryString.indexOf('&');
            if(index != -1){
                queryString = queryString.substring(0,index);
            }
            url = url + "?" + queryString;

            //2.获取到当前登录人允许的操作
            String authorStr = session.getAttribute("authorStr").toString();

            //6.放行 一律放行 如果想具体到每个管理是否放行时 需要到模块管理一个一个添加 视频08
            chain.doFilter(request,response);


            /*//3.比对本次操作是否在当前登录人允许的操作范围内
            if (authorStr.contains(url)) {
                //3.1如果允许，放行
                chain.doFilter(request,response);
                return;
            } else {
                //3.2不允许跳转到非法访问页
                response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
