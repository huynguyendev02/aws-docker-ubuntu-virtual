package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Image;
import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.util.List;

public class ImageService {
    public static Image findImageById(int imageId){
        String query = "select * from image where id= :imageId";
        try (Connection con = ConnectionUtils.openConnection()){
            Image image =  con.createQuery(query).addParameter("imageId",imageId).executeAndFetchFirst(Image.class);
            return image;
        }
    }

    public static void createImage(String nameImage, int idInstance, int userId) {
        Instance ins = InstanceService.findInsById(idInstance);
        int serverId = NetworkService.findNetworkById(ins.getNetworkId()).getServerId();
        String nameUser = UserService.getUsername(userId);
        HostSSHUtils.executeCommand("docker commit "+ins.getNameInstance()+" "+nameUser+"/"+nameImage, serverId);

        String query = "insert into image (nameImage, serverId, type, sshMethod, userId) " +
                "values (:nameImage, :serverId, :type, :sshMethod, :userId)";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query)
                    .addParameter("nameImage",nameUser+"/"+nameImage)
                    .addParameter("serverId",serverId)
                    .addParameter("type", ins.getImageId())
                    .addParameter("sshMethod", ins.getKeyId()==0 ? 0:1)
                    .addParameter("userId",userId)
                    .executeUpdate();
        }
    }

    public static void removeImage(int imageId){
        String query1 = "select * from instance where imageId=:imageId";
        try (Connection con = ConnectionUtils.openConnection()){
            Instance check=  con.createQuery(query1)
                    .addParameter("imageId",imageId)
                    .executeAndFetchFirst(Instance.class);
            if (check != null) return;

        }
        Image image = ImageService.findImageById(imageId);
        HostSSHUtils.executeCommand("docker image rm "+image.getNameImage(), image.getServerId());

        String query2 = "delete from image where id= :imageId";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query2)
                    .addParameter("imageId",imageId)
                    .executeUpdate();
        }
    }
    public static List<Image> findAllByUserId(int userId) {
        String query1 = "select * from image where userId=:userId";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query1)
                    .addParameter("userId",userId)
                    .executeAndFetch(Image.class);
        }
    }
    public static List<Image> findAll(){
        String query = "select * from image";
        try (Connection con = ConnectionUtils.openConnection()){
            return   con.createQuery(query).executeAndFetch(Image.class);

        }
    }
}
