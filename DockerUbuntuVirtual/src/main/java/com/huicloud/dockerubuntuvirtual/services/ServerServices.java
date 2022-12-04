package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import org.sql2o.Connection;

public class ServerServices {
    public static Server findServerById(int serverId){
        String query = "select * from server where id= :serverId";
        try (Connection con = ConnectionUtils.openConnection()){
          Server server =  con.createQuery(query).addParameter("serverId",serverId).executeAndFetchFirst(Server.class);
          return server;
        }
    }
}
