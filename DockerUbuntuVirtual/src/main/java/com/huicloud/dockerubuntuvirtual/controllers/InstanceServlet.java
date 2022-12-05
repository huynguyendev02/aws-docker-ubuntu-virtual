package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.services.NetworkService;
import com.huicloud.dockerubuntuvirtual.services.SSHKeyService;
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

        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
                List<Instance> list = InstanceService.findAll();
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            case "/Launch":
                List<SSHKey> list3 = SSHKeyService.findAll();
                request.setAttribute("SSHKeys", list3);


                List<Network> list4 = NetworkService.findAll();
                request.setAttribute("Networks", list4);
                ServerUtils.foward("/viewMain/viewInstance/Launch.jsp", request, response);
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name, id, state, type, ssh, ip, monitor, lunchTime;
        name = request.getParameter("NameInstance");
        type = "";
        ssh = "";
        monitor = "";

        //        cái này lấy ở dưới Data
        id = "1";
        state = "Processing";
        ip = "192.168.12.13";
        lunchTime = "12-12-12";
        List<Instance> list = InstanceService.findAll();

        String url = request.getPathInfo();

        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            case "/Launch":
                System.out.println(request.getParameter("NameInstance"));
                System.out.println(request.getParameter("OS"));
                System.out.println(request.getParameter("CPUS"));
                System.out.println(request.getParameter("Memory"));
                System.out.println(request.getParameter("terminate"));




                Instance yeah = new Instance(12, name, 123, 3, 8080, 2, 12, 15, "Processing", 15);                list.add(yeah);
                request.setAttribute("instances", list);
                ServerUtils.foward("/viewMain/Instance.jsp", request, response);
                break;
            default:
                break;
        }
    }
}
