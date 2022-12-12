package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.*;
import com.huicloud.dockerubuntuvirtual.services.*;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;
import org.sql2o.logging.SysOutLogger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ImageServlet", value = "/Main/Image/*")
public class ImageServlet extends HttpServlet {
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
                    List<Image> imageList =  ImageService.findAll();
                    for (Image img:
                         imageList) {
                        System.out.print(img.getIPSever());
                    }
                    request.setAttribute("images",imageList);
                    ServerUtils.foward("/viewAdmin/AdImage.jsp", request, response);
                }
                else {
                    List<Image> imageList =  ImageService.findAllByUserId((Integer) session.getAttribute("userId"));
                    request.setAttribute("images",imageList);
                    ServerUtils.foward("/viewMain/Image.jsp", request, response);
                }
                break;
            case "/LaunchInstanceFromImage":

                ServerUtils.foward("/viewMain/viewImage/LaunchInstanceFromImage.jsp", request, response);
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
                String idImage = request.getParameter("IdImage");
                String idAction= request.getParameter("IdAction");
//                System.out.println("ID Image: " + idImage);
//                System.out.println("ID Action: " + idAction);
                if (Integer.parseInt(idAction)==0) {
                    ImageService.removeImage(Integer.parseInt(idImage));
                    response.sendRedirect(request.getContextPath()+"/Main/Image");
                }
                else {
                    String idServer = request.getParameter("IdServer");
//                    String idOS = request.getParameter("IdOS");
                    String checkSSH = request.getParameter("IdSSH");

                    Server server = ServerServices.findServerById(Integer.valueOf(idServer));
                    Image os = ImageService.findImageById(Integer.valueOf(idImage));

                    System.out.print(idServer);
                    System.out.print(idServer);


                    List<Network> NetworkListByServer = NetworkService.findNetworkByServerId(Integer.parseInt(idServer),(Integer) session.getAttribute("userId"));
                    for (Network net:NetworkListByServer
                         ) {
                        System.out.print(net.getNameNetwork());
                    }
                    request.setAttribute("server", server);
                    request.setAttribute("os", os);
                    request.setAttribute("Networks", NetworkListByServer);

//                    checkSSH = 0: tức là dùng pass
//                    checkSSH = 1: tức là dùng key
                    if (checkSSH.equals("0"))
                    {
                        SSHKey temp = new SSHKey(-1, "SSH Key fake", -1);
                        request.setAttribute("temp", temp);

                        List<SSHKey> SSHList = new ArrayList<>();
                        SSHList.add(temp);
                        request.setAttribute("SSHKeys", SSHList);
                    }
                    else {
                        SSHKey temp = new SSHKey(1, "SSH Key fake", 1);
                        request.setAttribute("temp", temp);

                        List<SSHKey> SSHList = SSHKeyService.findAllById((Integer) session.getAttribute("userId"));
                        request.setAttribute("SSHKeys", SSHList);
                    }


                    ServerUtils.foward("/viewMain/viewImage/LaunchInstanceFromImage.jsp", request, response);
                }
                break;
            case "/LaunchInstanceFromImage":
                String nameIns = request.getParameter("NameInstance");
                int osId =   Integer.parseInt(request.getParameter("OS")) ;
                double cpus = Double.parseDouble(request.getParameter("CPUS"));
                double mem =  Double.parseDouble(request.getParameter("Memory"));
                String terminateState = request.getParameter("terminate");
//                System.out.print(Integer.parseInt(request.getParameter("SSHKey")));

//                sshId trả về -1 tức là dùng pass
//                 sshId trả về số khác, tức là trả về id của sshkey
                int sshId =Integer.parseInt(request.getParameter("SSHKey")) ;
                int netId  =Integer.parseInt(request.getParameter("Network"));
                String userData  =request.getParameter("UserData");
//                ID server mới lấy
                int serverId =Integer.parseInt(request.getParameter("Server")) ;

                InstanceService.createInstance(nameIns, cpus, mem,netId,(Integer) session.getAttribute("userId"),osId,sshId == -1 ? 0:sshId);


                System.out.println("Name: " + nameIns);
                System.out.println("OS id: " + osId);
                System.out.println("CPUS: " + cpus);
                System.out.println("mem: " + mem);
                System.out.println("terminate: " + terminateState);
                System.out.println( "ssh: " + sshId);
                System.out.println("Server: " + serverId);
                System.out.println("netID: " + netId);
                System.out.println("UserData: " + userData);

                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            default:
                break;
        }
    }
}
