package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;

import com.huicloud.dockerubuntuvirtual.services.SSHKeyService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/Main/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        RequestDispatcher rd;
        switch (url){
            case "/Instance":
                List<Instance> list = InstanceService.findAll();
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            case "/Snapshot":
                ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                break;
            case "/SSH":
                List<SSHKey> list2 = SSHKeyService.findAll();
                request.setAttribute("SSHKeys", list2);
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
