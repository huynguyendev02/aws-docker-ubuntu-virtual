package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Instance;

import com.huicloud.dockerubuntuvirtual.services.InstanceService;
import com.huicloud.dockerubuntuvirtual.services.SSHKeyService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


@WebServlet(name = "SSHServlet", value = "/Main/SSH/*")
public class SSHServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();

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
                ServletContext context = getServletContext();
                String fullPath = context.getRealPath("/key.pem");

                Path path = Paths.get(fullPath);
                byte[] data = Files.readAllBytes(path);
                response.setContentType("application/x-pem-file");
                response.setHeader("Content-disposition", "attachment; filename=key.pem");
                response.setContentLength(data.length);
                InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outStream.close();



//                String key = (String) session.getAttribute("SSHKey");
//                System.out.print(session.getAttribute("SSHKey"));
//                // Hello
//                PrintWriter out = response.getWriter();
//                out.println("<html><body>");
//                out.println(key);
//                out.println("</body></html>");
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();

        System.out.print(request.getAttribute("pathKey"));
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url){
            case "/":

                ServerUtils.foward("/viewMain/Ssh.jsp", request, response);
                break;
            case "/Create":
                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                System.out.print(path);
                try {
                    String key = SSHKeyService.addKey((Integer) session.getAttribute("userId"),request.getParameter("nameKey"), path);
                    session.setAttribute("SSHKey",key);

                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath() + "/Main/SSH/ShowPrivateKey");
                break;

            default:
                break;
        }
    }
}
