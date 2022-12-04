package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;
import org.sql2o.Connection;

import java.util.List;

public class InstanceService {
    public static void createImage(String nameInstance, double cpus, double memory, int networkId, int userId, int imageId, int keyId){
        Network net = NetworkService.findNetworkById(networkId);
        Server ser = ServerServices.findServerById(net.getServerId());

       List<Instance> instances;
       String query1 = "select * from instance";
       try (Connection con = ConnectionUtils.openConnection()){
           instances = con.createQuery(query1)
                   .executeAndFetch(Instance.class);
       }
       int port =0;
       if (instances.size()==0) {
           port = 20000;
       } else {
           String serverIp = ser.getIpServer();
           for (Instance ins: instances) {
               if (ServerServices.findServerById(NetworkService.findNetworkById(ins.getNetworkId()).getServerId()).getIpServer().equals( ser.getIpServer()) && ins.getport()>port )
                    port = ins.getport();
           }
           port+=1;
       }
       if (imageId==1){
           HostSSHUtils.executeCommand("docker run -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " +UserService.getUsername(userId)+":" + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage());
       } else {
           HostSSHUtils.executeCommand("docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " +UserService.getUsername(userId)+":" + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+"/usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"");
       }
        HostSSHUtils.executeCommand("docker cp /home/ubuntu/KEYSSH/"+SSHKeyService.getNameById(keyId)+".pub "+UserService.getUsername(userId) +"0"+nameInstance+":/home/sshuser/.ssh/authorized_keys");

        String query2 = "insert into instance ( nameInstance, cpus, memory, port, networkId, userId, imageId, state, keyId  ) " +
                "values ( :nameInstance, :cpus, :memory, :port, :networkId, :userId, :imageId, :state, :keyID )";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query2,true)
                    .addParameter("nameInstance",UserService.getUsername(userId) +"0"+nameInstance)
                    .addParameter("cpus",cpus)
                    .addParameter("memory",memory)
                    .addParameter("port",port)
                    .addParameter("networkId",networkId)
                    .addParameter("userId",userId)
                    .addParameter("imageId",imageId)
                    .addParameter("state","Running")
                    .addParameter("keyId",keyId)
                    .executeUpdate()
                    .getKey();
        }
    }
}
