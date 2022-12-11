package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.services.UserService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/Home/*")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        System.out.print(url);
        RequestDispatcher rd;
        switch (url) {
            case "/Login":
                ServerUtils.foward("/viewLogin/Login.jsp", request, response);
                break;
            case "/Register":
                ServerUtils.foward("/viewLogin/Register.jsp", request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();


        System.out.print(url);
        switch (url) {
            case "/Login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = UserService.checkCredentials(username,password);
                if (user != null) {

                    session.setAttribute("userId" , user.getId());
                    session.setAttribute("username" , user.getUsername());
                    session.setAttribute("type",user.getType());
                    List<Instance> list2 = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                    request.setAttribute("instances", list2);

                    response.sendRedirect(request.getContextPath() + "/Main/Instance");

                }
                else {
                    ServerUtils.foward("/viewLogin/Login.jsp", request, response);
                }
                break;
            case "/Register":
                String newUsername = request.getParameter("newUsername");
                String newPassword = request.getParameter("newPassword");
                System.out.print(newUsername + newPassword);
                UserService.register(newUsername,newPassword);

                response.sendRedirect(request.getContextPath() + "/Home/Login");
                break;
            default:
                break;
        }
    }
}
