package com.example.servlet;

import com.example.domain.User;
import com.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user = userService.findUser(username, password);
        System.out.println(user);
        HttpSession session = request.getSession();
        if (user != null ) {
            response.getWriter().write("登录成功");
            response.getWriter().write("<a href='FindStudentByPageServlet'>查询学生信息</a>");
            session.setAttribute("user",user);
        } else {
            request.setAttribute("loginInfo","用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
