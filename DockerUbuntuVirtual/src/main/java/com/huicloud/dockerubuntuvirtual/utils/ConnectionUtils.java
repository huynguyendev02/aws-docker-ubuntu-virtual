package com.huicloud.dockerubuntuvirtual.utils;

import com.huicloud.dockerubuntuvirtual.config.Config;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class ConnectionUtils {
    static Sql2o sql2o = new Sql2o("jdbc:mysql://54.175.98.141:3306/"+ Config.databaseName, Config.usernameDB,Config.passwordDB);
    public static Connection openConnection(){
        return sql2o.open();

    }
}
