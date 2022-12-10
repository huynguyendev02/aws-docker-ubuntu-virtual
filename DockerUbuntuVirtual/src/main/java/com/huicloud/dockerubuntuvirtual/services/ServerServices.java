package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.time.LocalDate;
import java.util.List;

public class ServerServices {
    public static Server findServerById(int serverId){
        String query = "select * from server where id= :serverId";
        try (Connection con = ConnectionUtils.openConnection()){
          Server server =  con.createQuery(query).addParameter("serverId",serverId).executeAndFetchFirst(Server.class);
          return server;
        }
    }
    public static List<Server> findAll(){
        String query = "select * from server";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query).executeAndFetch(Server.class);
        }
    }
    public static void addServer(String ipAddress) {
        String token = HostSSHUtils.executeCommand("docker swarm join-token worker");

        String query = "insert into server ( ipServer, state) " +
                "values ( :ipServer, :state)";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query)
                    .addParameter("ipServer",ipAddress)
                    .addParameter("state","Running")
                    .executeUpdate();
        }
    }
}
