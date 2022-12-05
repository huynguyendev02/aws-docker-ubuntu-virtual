package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;
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
        System.out.print(url);
        switch (url) {
            case "/Login":
                String UserName = request.getParameter("username");
                String Pass = request.getParameter("password");

//                Kiểm tra tài khoản


                if (UserName.toString().equals("hehe")) {
                    List<Instance> list2 = InstanceService.findAll();
                    request.setAttribute("instances", list2);
                    ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                } else
                    ServerUtils.foward("/viewLogin/Login.jsp", request, response);
                break;
            case "/Register":
                String newUserName = request.getParameter("newUserName");
                String newPassword = request.getParameter("newPassword");
                String confirmNewPassword = request.getParameter("confirmNewPassword");

//                Hàm thêm tài khoản

                ServerUtils.foward("/viewLogin/Login.jsp", request, response);
                break;
            default:
                break;
        }
    }
}
