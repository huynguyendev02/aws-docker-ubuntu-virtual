package com.huicloud.dockerubuntuvirtual.controllers;

import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;
import com.huicloud.dockerubuntuvirtual.services.UserService;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/Main/User")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        if (url == null || url.equals("/")) {
            url = "/";
        }
        switch (url) {
            case "/":
 //                Tạo list User
                User cc = new User(1, "keke", "123", 1);
                User cc2 = new User(1, "kaka", "123", 1);
                List<User> userList = new ArrayList<>();
                userList.add(cc);
                userList.add(cc2);

                request.setAttribute("Users", userList);
                if (UserService.check()==0){
                    ServerUtils.foward("/viewAdmin/AdUsers.jsp", request, response);
                }
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
//                0: ấn nút xóa
//                1: ấn nút edi
//                2:ấn nút tạo server mới
                String idAction = request.getParameter("IdAction");
                System.out.println(idAction);
                if (idAction.equals("0"))
                {
//                    Id server chuẩn bị xóa
                    String idServer = request.getParameter("IdUser");
                    System.out.println("ID: " + idServer);
                }
                if(idAction.equals("1"))
                {
//                    Id server chuẩn bị edit
                    String idServer = request.getParameter("IdUser");
                    String newUsername = request.getParameter("NewUsername");
                    String newPassword = request.getParameter("NewPassword");
                    System.out.println("ID: " + idServer);
                    System.out.println("NewUserame: " + newUsername);
                    System.out.println("NewPass: " + newPassword);
                }
                if(idAction.equals("2"))
                {
//                    Username
                    String username = request.getParameter("Username");
                    System.out.println(username);
//                    Pass
                    String password = request.getParameter("Password");
                    System.out.println(password);
                }
                response.sendRedirect(request.getContextPath() + "/Main/User");
                break;
            default:
                break;
        }
    }
}
