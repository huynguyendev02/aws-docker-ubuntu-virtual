package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.*;
import com.huicloud.dockerubuntuvirtual.services.*;

import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/Main/*")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        RequestDispatcher rd;

        switch (url){
            case "/Instance":
                List<Instance> listInstances = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                request.setAttribute("instances", listInstances);
                System.out.print(UserService.check(session));
                if (UserService.check(session)==0){
                    ServerUtils.foward("/viewAdmin/AdInstance.jsp", request, response);
                }
                else {
                    ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                }
                break;
            case "/Snapshot":
                List<Snapshot> listSnaps = SnapshotService.findAll();
                request.setAttribute("Snapshots", listSnaps);
                ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                break;
            case "/Image":
                ServerUtils.foward("/viewMain/Image.jsp", request, response);
                break;
            case "/SSH":
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Network":
                List<Network> listNetwork = NetworkService.findAllById((Integer) session.getAttribute("userId"));
                request.setAttribute("Networks", listNetwork);
                if (UserService.check(session)==0){
                    ServerUtils.foward("/viewAdmin/AdNetwork.jsp", request, response);
                }
                else {
                    ServerUtils.foward("/viewMain/Network.jsp", request, response);
                }
                break;
            case "/Server":
                List<Server> listServer = ServerServices.findAll();
                request.setAttribute("Servers", listServer);
                if (UserService.check(session)==0){
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
        RequestDispatcher rd;
        switch (url){
            case "/Instance":
                String IdInstance = request.getParameter("IdInstance");
                String IdAction = request.getParameter("IdAction");
                System.out.println("Hello");
                System.out.println(IdInstance);
                System.out.println(IdAction);

                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            case "/Snapshot":
                String cc = request.getParameter("IdInstance");
                System.out.println(cc);
//                request.setAttribute("instances", list);
//                ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                break;
            case "/SSH":
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Server":
                System.out.println("Hello");
                break;
            default:
                break;


        }
    }
}
