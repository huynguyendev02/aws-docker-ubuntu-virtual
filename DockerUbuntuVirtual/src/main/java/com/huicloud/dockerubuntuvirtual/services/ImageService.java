package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Image;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import org.sql2o.Connection;

public class ImageService {
    public static Image findImageById(int imageId){
        String query = "select * from image where id= :imageId";
        try (Connection con = ConnectionUtils.openConnection()){
            Image image =  con.createQuery(query).addParameter("imageId",imageId).executeAndFetchFirst(Image.class);
            return image;
        }
    }
}
