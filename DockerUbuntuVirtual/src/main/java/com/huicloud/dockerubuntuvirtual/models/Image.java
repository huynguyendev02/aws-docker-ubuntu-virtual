package com.huicloud.dockerubuntuvirtual.models;

public class Image {
    int id;
    String nameImage;
    int serverId;
    int type;
    int sshMethod;

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
}
