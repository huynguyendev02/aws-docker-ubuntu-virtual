package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.math.BigInteger;
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
        String query = "insert into server ( ipServer, state) " +
                "values ( :ipServer, :state)";
        BigInteger newServerId;
        try (Connection con = ConnectionUtils.openConnection()) {
            newServerId = (BigInteger) con.createQuery(query, true)
                    .addParameter("ipServer", ipAddress)
                    .addParameter("state", "Running")
                    .executeUpdate()
                    .getKey();
        }
        String token = HostSSHUtils.executeCommand("docker swarm join-token worker",1).split("\n")[2].trim();
        System.out.print( newServerId.intValue());
        HostSSHUtils.executeCommand(token, newServerId.intValue());

    }
}
