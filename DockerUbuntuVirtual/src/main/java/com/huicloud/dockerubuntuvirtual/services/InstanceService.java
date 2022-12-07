package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
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
       String commandUbuntuKey = "docker run -d -p "+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage();
       String commandUbuntuPass = "docker run -d -p "+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+"pass";
       String commandCentosKey = "docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+" /usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"";
       String commandCentosPass = "docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+"pass /usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"";

       if (keyId==0) {
           if (imageId ==1)
               HostSSHUtils.executeCommand(commandUbuntuPass);
           else
               HostSSHUtils.executeCommand(commandCentosPass);
       } else {
           if (imageId ==1)
               HostSSHUtils.executeCommand(commandUbuntuKey);
           else
               HostSSHUtils.executeCommand(commandCentosKey);
           HostSSHUtils.executeCommand("sudo docker cp /home/ubuntu/KEYSSH/"+UserService.getUsername(userId)+"/"+SSHKeyService.getNameById(keyId)+"pub.pem "+UserService.getUsername(userId) +"0"+nameInstance+":/home/sshuser/.ssh/authorized_keys");
       }

        String query2 = "insert into instance ( nameInstance, cpus, memory, port, networkId, userId, imageId, state, keyId  ) " +
                "values ( :nameInstance, :cpus, :memory, :port, :networkId, :userId, :imageId, :state, :keyId )";
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
                    .addParameter("keyId",keyId==0 ? null : keyId )
                    .executeUpdate()
                    .getKey();
        }
    }


//    =======================================
    public static List<Instance> findAllByUserId(int userId) {
        String query1 = "select * from instance where userId=:userId";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query1)
                    .addParameter("userId",userId)
                    .executeAndFetch(Instance.class);
        }
    }
}
