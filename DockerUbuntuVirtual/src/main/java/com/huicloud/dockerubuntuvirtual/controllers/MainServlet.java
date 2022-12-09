package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;

import com.huicloud.dockerubuntuvirtual.services.SnapshotService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            case "/Snapshot":
                List<Snapshot> listSnaps = SnapshotService.findAll();
                request.setAttribute("Snapshots", listSnaps);
                ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                break;
            case "/SSH":
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
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

                List<Instance> list = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
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
            default:
                break;
        }
    }
}
