package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

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

        HostSSHUtils.executeCommand("mkdir /home/ubuntu/KEYSSH/"+username);

        String query = "insert into user ( username, password ) values ( :username, :password )";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query,true)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .executeUpdate()
                    .getKey();
        }
    }

}
