package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.services.NetworkService;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NetworkServlet", value = "/Main/Network/*")
public class NetworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();

        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
                List<Network> list3 = NetworkService.findAllById(1);
                request.setAttribute("Networks", list3);
                ServerUtils.foward("/viewMain/Network.jsp", request, response);
                break;
            case "/Create":
                List<Server> list4 = ServerServices.findAll();
                request.setAttribute("Servers", list4);
                ServerUtils.foward("/viewMain/viewNetwork/CreateNetwork.jsp", request, response);
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        switch (url) {
            case "/Create":
                String ServerID = request.getParameter("ServerID");


                List<Server> list4 = ServerServices.findAll();
                request.setAttribute("Servers", list4);
                ServerUtils.foward("/viewMain/viewNetwork/CreateNetwork.jsp", request, response);
                break;

            default:
                break;
        }
    }
}
