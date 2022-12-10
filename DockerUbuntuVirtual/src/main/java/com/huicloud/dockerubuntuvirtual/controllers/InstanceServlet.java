package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.services.NetworkService;
import com.huicloud.dockerubuntuvirtual.services.SSHKeyService;
import com.huicloud.dockerubuntuvirtual.services.SnapshotService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateServlet", value = "/Main/Instance/*")
public class InstanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":

                List<Instance> list = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            case "/Launch":
                List<SSHKey> list3 = SSHKeyService.findAllById((Integer) session.getAttribute("userId"));
                request.setAttribute("SSHKeys", list3);


                List<Network> list4 = NetworkService.findAllById((Integer) session.getAttribute("userId"));
                request.setAttribute("Networks", list4);
                ServerUtils.foward("/viewMain/viewInstance/Launch.jsp", request, response);
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
                int idInstance = Integer.parseInt(request.getParameter("IdInstance"));
                int idAction = request.getParameter("IdAction").isEmpty() ? 0:Integer.parseInt(request.getParameter("IdAction"));

                String snapshotName = request.getParameter("SnapshotName");
                System.out.print(snapshotName);
                String check = request.getParameter("State");
                // 0 = change state instance
                // !=0  => create snapshot
                if (check.equals("0")){
                    if (idAction==1)
                        InstanceService.startIns(idInstance);
                    if (idAction==2)
                        InstanceService.stopIns(idInstance);
                    if (idAction==3)
                        InstanceService.terminateIns(idInstance);

                    List<Instance> list = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                    request.setAttribute("instances", list);
                    ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                }
                else {
                    SnapshotService.createSnap(snapshotName, idInstance);
                    List<Snapshot> listSnaps = SnapshotService.findAll();
                    request.setAttribute("Snapshots", listSnaps);
                    ServerUtils.foward("/viewMain/Snap.jsp", request, response);
                }

                break;
            case "/Launch":
                String nameIns = request.getParameter("NameInstance");
                int osId =   Integer.parseInt(request.getParameter("OS")) ;
                double cpus = Double.parseDouble(request.getParameter("CPUS"));
                double mem =  Double.parseDouble(request.getParameter("Memory"));
                String terminateState = request.getParameter("terminate");
                System.out.print(Integer.parseInt(request.getParameter("SSHKey")));
                int sshId =Integer.parseInt(request.getParameter("SSHKey")) ;
                int netId  =Integer.parseInt(request.getParameter("Network"));

                InstanceService.createInstance(nameIns,cpus,mem,netId,(Integer) session.getAttribute("userId"),osId,sshId);
                List<Instance> listIns =InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));

                request.setAttribute("instances", listIns);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            default:
                break;
        }
    }
}
