package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.services.*;
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
                List<Instance> listInstances = InstanceService.findAllByUserId((Integer) session.getAttribute("userId"));
                request.setAttribute("instances", listInstances);
                if (UserService.check()==0){
                    ServerUtils.foward("/viewAdmin/AdInstance.jsp", request, response);
                }
                else {
                    ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                }
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
//                Tên của Snap mới tạo
                String snapshotName = request.getParameter("SnapshotName");
//                Tên của Image mới tạo
                String imageName = request.getParameter("ImageName");

                String check = request.getParameter("State");

//                0: tạo Instace mới, quay lại trang Instance
//                1:  tạo Sanp mới từ Instance mới chọn, quay lại trang Snap
//                2:  tạo Image mới từ Instance mới chọn, quay lại trang Image
                if (check.equals("0")){

                    if (idAction==1)
                        InstanceService.startIns(idInstance);
                    if (idAction==2)
                        InstanceService.stopIns(idInstance);
                    if (idAction==3)
                        InstanceService.terminateIns(idInstance);

                    response.sendRedirect(request.getContextPath() + "/Main/Instance");

                }
                if (check.equals("1")){

                    SnapshotService.createSnap(snapshotName, idInstance);
                    response.sendRedirect(request.getContextPath() + "/Main/Snapshot");

                }
                if (check.equals("2")){

                    System.out.println(idInstance);
                    System.out.println(imageName);

                    response.sendRedirect(request.getContextPath() + "/Main/Image");
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
                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            default:
                break;
        }
    }
}
