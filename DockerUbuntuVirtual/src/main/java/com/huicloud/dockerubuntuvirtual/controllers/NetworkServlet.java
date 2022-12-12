package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.services.NetworkService;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;
import com.huicloud.dockerubuntuvirtual.services.UserService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NetworkServlet", value = "/Main/Network/*")
public class NetworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":

                if (UserService.check(session) == 0){
                    List<Network> listNetwork = NetworkService.findAll();
                    request.setAttribute("Networks", listNetwork);
                    ServerUtils.foward("/viewAdmin/AdNetwork.jsp", request, response);
                }
                else {
                    List<Network> listNetwork = NetworkService.findAllById((Integer) session.getAttribute("userId"));
                    request.setAttribute("Networks", listNetwork);
                    ServerUtils.foward("/viewMain/Network.jsp", request, response);
                }
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
        HttpSession session = request.getSession();
        switch (url) {
            case "/Create":
                String serverId = request.getParameter("ServerID");
                String nameNetwork = request.getParameter("nameNetwork");

                NetworkService.addNetwork((Integer)session.getAttribute("userId"),nameNetwork, Integer.parseInt(serverId) );
                response.sendRedirect(request.getContextPath() + "/Main/Network");
                break;
            default:
                break;
        }
    }
}
