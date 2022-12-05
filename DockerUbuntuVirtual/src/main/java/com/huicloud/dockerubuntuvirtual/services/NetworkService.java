package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkService {
    public static void addNetwork(int userId, String nameNetwork, int serverId){
        String query = "insert into server ( nameNetwork, userId, serverId ) values ( :nameNetwork, :userId, :serverId )";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query,true)
                    .addParameter("nameNetwork", UserService.getUsername(userId) +":"+nameNetwork)
                    .addParameter("userId",userId)
                    .addParameter("serverId",serverId)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Network findNetworkById(int networkId) {
        String query = "select * from network where id= :networkId";
        try (Connection con = ConnectionUtils.openConnection()){
            Network network =  con.createQuery(query).addParameter("networkId",networkId).executeAndFetchFirst(Network.class);
            return network;
        }
    }
//    public static List<Network> findAll() {
//        String query = "select * from network";
//        try (Connection con = ConnectionUtils.openConnection()) {
//            return con.createQuery(query).executeAndFetch(Network.class);
//        }
//    }
public static List<Network> findAll() {
    return new ArrayList<>(
            Arrays.asList(
                    new Network(1, "Mạng của dương quá", 12, 12),
                    new Network(2, "Mạng của cô cô", 12, 12)
            )
    );
}
}
