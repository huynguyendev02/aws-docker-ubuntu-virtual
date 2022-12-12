package com.huicloud.dockerubuntuvirtual.models;

import com.huicloud.dockerubuntuvirtual.services.ImageService;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;

public class Image {
    int id;
    String nameImage;
    int serverId;
    int type;
    int sshMethod;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    int userId;

    public int getSshMethod() {
        return sshMethod;
    }

    public void setSshMethod(int sshMethod) {
        this.sshMethod = sshMethod;
    }

    public Image(int id, String nameImage, int serverId, int type, int sshMethod) {
        this.id = id;
        this.nameImage = nameImage;
        this.serverId = serverId;
        this.type = type;
        this.sshMethod = sshMethod;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Image(int id, String nameImage, int serverId, int type) {
        this.id = id;
        this.nameImage = nameImage;
        this.serverId = serverId;
        this.type = type;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public Image(int id, String nameImage, int serverId) {
        this.id = id;
        this.nameImage = nameImage;
        this.serverId = serverId;
    }

    public Image(int id, String nameImage) {
        this.id = id;
        this.nameImage = nameImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getIPSever(){
        if (ServerServices.findServerById(this.serverId) == null)
            return "";
        return ServerServices.findServerById(this.serverId).ipServer;
    }
    public String getOS(){
        return ImageService.findImageById(this.type).nameImage;
    }
    public String getSSHMethod(){
        return this.sshMethod == 0 ? "Password" : "RSA 4096bits";
    }
}
