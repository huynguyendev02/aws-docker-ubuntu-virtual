package com.huicloud.dockerubuntuvirtual.controllers;

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
                if (UserService.check() == 0){
                    ServerUtils.foward("/viewAdmin/AdImage.jsp", request, response);
                }
                else {
                    ImageService.findAllByUserId((Integer) session.getAttribute("userId"));
                    ServerUtils.foward("/viewMain/Image.jsp", request, response);
                }
                break;
            case "/LaunchInstanceFromImage":
                ServerUtils.foward("/viewMain/viewImage/LaunchInstanceFormImage.jsp", request, response);
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
                if (Integer.parseInt(idAction)==0) {
                    ImageService.removeImage(Integer.parseInt(idImage));
                }
                break;
            case "/LaunchInstanceFromImage":
                response.sendRedirect(request.getContextPath() + "/Main/Instance");
                break;
            default:
                break;
        }
    }
}
