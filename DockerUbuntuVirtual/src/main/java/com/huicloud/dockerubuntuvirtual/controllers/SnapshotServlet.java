package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.services.*;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;
import org.sql2o.Connection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SnapshotServlet", value = "/Main/Snapshot/*")
public class SnapshotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
                List<Snapshot> listSnaps = SnapshotService.findAll();
                request.setAttribute("Snapshots", listSnaps);

                if (UserService.check() == 0){
                    ServerUtils.foward("/viewAdmin/AdSnap.jsp", request, response);
                }
                else {
                    ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                }
                break;
            case "/LaunchInstanceFromSnapshot":
                String idSnap = request.getParameter("IdSnapshot");

                Snapshot snap = new Snapshot(1, "CentOS ", 12, 12);
                request.setAttribute("Snapshot", snap);


                List<SSHKey> list3 = SSHKeyService.findAllById((Integer) session.getAttribute("userId"));
                request.setAttribute("SSHKeys", list3);
                List<Network> list4 = NetworkService.findAllById((Integer) session.getAttribute("userId"));
                request.setAttribute("Networks", list4);

                ServerUtils.foward("/viewMain/viewSnapshot/LaunchSnapshot.jsp", request, response);
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
//                ID của Snap mới chọn
                String idSnap = request.getParameter("IdSnapshot");


                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            case "/LaunchInstanceFromSnapshot":

                String nameIns = request.getParameter("NameInstance");
                int osId =   Integer.parseInt(request.getParameter("OS")) ;
                double cpus = Double.parseDouble(request.getParameter("CPUS"));
                double mem =  Double.parseDouble(request.getParameter("Memory"));
                String terminateState = request.getParameter("terminate");
                System.out.print(Integer.parseInt(request.getParameter("SSHKey")));
                int sshId =Integer.parseInt(request.getParameter("SSHKey")) ;
//                int netId  =Integer.parseInt(request.getParameter("Network"));

                System.out.println(nameIns);
                System.out.println(osId);
                System.out.println(cpus);
                System.out.println(mem);
                System.out.println(terminateState);
                System.out.println(sshId);
//                System.out.println(netId);


                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            default:
                break;
        }
    }
}
