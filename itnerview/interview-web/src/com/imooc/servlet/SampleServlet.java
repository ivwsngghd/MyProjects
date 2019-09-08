package com.imooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SampleServlet extends HttpServlet {
    public SampleServlet() {
        System.out.println("正在创建Servlet");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化Servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.hashCode() + "：Service()正在提供服务");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print(this.hashCode() + "：Service()正在提供服务");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁Servlet");
    }
}
