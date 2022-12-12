package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Image;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.services.ImageService;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;
import com.huicloud.dockerubuntuvirtual.services.UserService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServerServlet", value = "/Main/Server/*")
public class ServerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
                List<Server> listServer = ServerServices.findAll();
                request.setAttribute("Servers", listServer);
                if (UserService.check()==0){
                    ServerUtils.foward("/viewAdmin/AdServer.jsp", request, response);
                }
                else {
                    ServerUtils.foward("/viewMain/Server.jsp", request, response);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
//                0: ấn nút xóa
//                1: ấn nút tạo server mới
                String idAction = request.getParameter("IdAction");
                System.out.println(idAction);
                if (idAction.equals("0"))
                {
//                    Id server chuẩn bị xóa
                    String idServer = request.getParameter("IdServer");
                    System.out.println("ID: " + idServer);
                }
                else{
//                    tên server mới tạo
                    String serverName = request.getParameter("ServerName");
                    System.out.println(serverName);
                }
                response.sendRedirect(request.getContextPath() + "/Main/Server");
                break;
            default:
                break;
        }
    }
}
