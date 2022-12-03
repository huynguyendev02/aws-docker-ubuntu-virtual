package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import org.sql2o.Connection;

public class UserService {
    public static User checkCredentials(String username, String password){
        try (Connection connection = ConnectionUtils.openConnection()) {
            String query =
                    "Select * from user"+
                    "Where username= :username && password= :password";
            return connection.createQuery(query)
                    .addParameter("username",username)
                    .addParameter("password",password)
                    .executeAndFetchFirst(User.class);

        }
    }

}
