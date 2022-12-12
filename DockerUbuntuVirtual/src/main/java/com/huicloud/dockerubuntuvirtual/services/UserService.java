package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    public static User checkCredentials(String username, String password){
        String query ="SELECT * " +
                "FROM user " +
                "WHERE username = :username AND password = :password";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .executeAndFetchFirst(User.class);
        }
    }
    public static List<User> findAll(){
        String query ="SELECT * " +
                "FROM user ";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query)
                    .executeAndFetch(User.class);
        }
    }
    public static void alterUser(int userId, String username, String password, int type){
        String query = "update user set username = :username, password= :password, type= :type where id = :userId";
        try (Connection con = ConnectionUtils.openConnection()){
             con.createQuery(query)
                     .addParameter("userId", userId)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .addParameter("type",type)
                    .executeUpdate();
        }
    }
    public static void removeUser(int userId){
        String query1 = "delete from user where id= :userId";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
                    .addParameter("userId",userId)
                    .executeUpdate();
        }
    }
    public static int checkUsername(String username) {
        String query ="SELECT COUNT(*) " +
                "FROM user " +
                "WHERE username = :username";
        try (Connection con = ConnectionUtils.openConnection()){
            int result = con.createQuery(query)
                    .addParameter("username",username)
                    .executeScalar(Integer.class);
            return result;
        }
    }

    public static String getUsername(int userId) {
        String query ="SELECT * " +
                "FROM user " +
                "WHERE id = :userId";
        try (Connection con = ConnectionUtils.openConnection()){
            User result = con.createQuery(query)
                    .addParameter("userId",userId)
                    .executeAndFetchFirst(User.class);
            return result.getUsername();
        }
    }
    public static void register(String username, String password) {

        for (Server server:
             ServerServices.findAll()) {
            HostSSHUtils.executeCommand("mkdir /home/ubuntu/KEYSSH/"+username,server.getId());
        }

        String query = "insert into user ( username, password , type) values ( :username, :password , :type)";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query,true)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .addParameter("type",1)
                    .executeUpdate()
                    .getKey();
        }
    }
    //    Trả về 0 nếu là Admin, trả về 1 nếu là User
    public static int check(HttpSession session){
        if ( (Integer)session.getAttribute("type") == 0)
            return 0;
        return 1;
    }
}
