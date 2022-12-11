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
        HostSSHUtils.executeCommand("docker commit "+ins.getNameInstance()+" "+nameUser+"/"+nameImage);

        String query = "insert into image (nameImage, serverId, type, sshMethod) " +
                "values (:nameImage, :serverId, :type, :sshMethod)";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query)
                    .addParameter("nameImage",nameUser+"/"+nameImage)
                    .addParameter("serverId",serverId)
                    .addParameter("type", ins.getImageId())
                    .addParameter("sshMethod", ins.getKeyId()==0 ? 0:1)
                    .executeUpdate();
        }
    }

    public static void removeImage(int imageId){
        Image image = ImageService.findImageById(imageId);
        HostSSHUtils.executeCommand("docker image rm "+image.getNameImage());

        String query1 = "delete from image where id= :imageId";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
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

}
