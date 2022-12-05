package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;

import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.services.SSHKeyService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "SSHServlet", value = "/Main/SSH/*")
public class SSHServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url){
            case "/":
                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Create":
                ServerUtils.foward("/viewMain/viewSsh/CreateSsh.jsp", request, response);
                break;
            case "/ShowPrivateKey":
                response.setContentType("text/html");
                HttpSession session = request.getSession();
                String key = (String) session.getAttribute("SSHKey");
                System.out.print(session.getAttribute("SSHKey"));
                // Hello
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println(key);
                out.println("</body></html>");
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url){
            case "/":

                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Create":
                HttpSession session = request.getSession();
                try {
                    String key = SSHKeyService.addKey(1,request.getParameter("nameKey") );
                    session.setAttribute("SSHKey",key);

                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath() + "/Main/SSH/ShowPrivateKey");
                break;

            default:
                break;
        }
    }
}
