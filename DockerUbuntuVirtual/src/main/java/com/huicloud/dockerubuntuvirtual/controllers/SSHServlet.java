package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;

import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SSHServlet", value = "/Main/SSH/*")
public class SSHServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url){
            case "/":
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Create":
                ServerUtils.foward("/viewMain/viewSsh/CreateSsh.jsp", request, response);
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url){
            case "/":

                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Create":
                String nameKey = request.getParameter("nameKey");

                // Thêm SSH key mới

                ServerUtils.foward("/viewMain/viewSsh/CreateSsh.jsp", request, response);
                break;

            default:
                break;
        }
    }
}
