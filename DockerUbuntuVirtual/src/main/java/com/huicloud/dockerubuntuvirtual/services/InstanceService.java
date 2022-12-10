package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Image;
import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.util.List;

public class InstanceService {
    public static void createInstance(String nameInstance, double cpus, double memory, int networkId, int userId, int imageId, int keyId){
        Network net = NetworkService.findNetworkById(networkId);
        Server ser = ServerServices.findServerById(net.getServerId());
        Image image = ImageService.findImageById(imageId);

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
       String commandUbuntuKey="", commandUbuntuPass="", commandCentosKey="", commandCentosPass="";
       if (imageId <3) {
           commandUbuntuKey = "docker run -d -p "+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage();
           commandUbuntuPass = "docker run -d -p "+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+"pass";
           commandCentosKey = "docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+" /usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"";
           commandCentosPass = "docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged huynguyendev02/docker-virtual:"+ImageService.findImageById(imageId).getNameImage()+"pass /usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"";
       } else
       {
           if (image.getType()==1) {
               commandUbuntuKey = "docker run -d -p "+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" "+image.getNameImage();
               commandUbuntuPass = commandUbuntuKey;
           } else {
               commandCentosKey = "docker run -dit -d -p"+port+":22 --cpus " + cpus +" --memory "+ memory +"G --net " + net.getNameNetwork() +" --name "+UserService.getUsername(userId)+ "0"+ nameInstance +" --privileged "+image.getNameImage()+" /usr/sbin/init \"systemctl start sshd; /usr/sbin/sshd -D\"";
               commandCentosPass =commandCentosKey;
           }
       }

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
    public static Instance findInsById(int idInstance){
        String query1 = "select * from instance where id=:id";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query1)
                    .addParameter("id",idInstance)
                    .executeAndFetchFirst(Instance.class);
        }
    }

    public static void startIns(int idInstance) {
        Instance ins = InstanceService.findInsById(idInstance);
        HostSSHUtils.executeCommand("docker start "+ins.getNameInstance());

        String query1 = "update instance set state= :state where id= :id";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
                    .addParameter("id",idInstance)
                    .addParameter("state", "Running")
                    .executeUpdate();
        }
    }
    public static void stopIns(int idInstance) {
        Instance ins = InstanceService.findInsById(idInstance);
        HostSSHUtils.executeCommand("docker stop "+ins.getNameInstance());
        String query1 = "update instance set state= :state where id= :id";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
                    .addParameter("id",idInstance)
                    .addParameter("state", "Stopped")
                    .executeUpdate();
        }
    }
    public static void terminateIns(int idInstance) {
        Instance ins = InstanceService.findInsById(idInstance);
        HostSSHUtils.executeCommand("docker stop "+ins.getNameInstance());
        HostSSHUtils.executeCommand("docker rm "+ ins.getNameInstance());
        String query1 = "delete from instance where id= :id";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
                    .addParameter("id",idInstance)
                    .executeUpdate();
        }

    }
    public static List<Instance> findAllByUserId(int userId) {
        String query1 = "select * from instance where userId=:userId";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query1)
                    .addParameter("userId",userId)
                    .executeAndFetch(Instance.class);
        }
    }
}
