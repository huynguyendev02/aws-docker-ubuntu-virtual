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


                if (UserService.check(session) == 0){
                    List<Snapshot> listSnaps = SnapshotService.findAll();
                    request.setAttribute("Snapshots", listSnaps);
                    ServerUtils.foward("/viewAdmin/AdSnap.jsp", request, response);
                }
                else {
                    List<Snapshot> listSnaps = SnapshotService.findAllByUserId((Integer) session.getAttribute("userId"));
                    request.setAttribute("Snapshots", listSnaps);
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
                System.out.print(request.getParameter("IdSnapshot"));
                System.out.print(request.getParameter("idAction"));

                int idSnap = Integer.parseInt(request.getParameter("IdSnapshot"));
                int idAction = Integer.parseInt(request.getParameter("idAction"));
                if (idAction==0) {
                    SnapshotService.removeSnap(idSnap);
                    response.sendRedirect(request.getContextPath() + "/Main/Snapshot");

                } else {
                    SnapshotService.restoreSnap(idSnap);
                    response.sendRedirect(request.getContextPath() + "/Main/Instance");
                }
                break;
            default:
                break;
        }
    }
}
