package com.huicloud.dockerubuntuvirtual.models;

public class Container {
    int id;
    String nameContainer;
    int imageId;
    int userId;
    float cpus;
    float ram;

    public Container() {
    }

    public Container(int id, String nameContainer, int imageId, int userId, float cpus, float ram, String ipSSH, int serverId) {
        this.id = id;
        this.nameContainer = nameContainer;
        this.imageId = imageId;
        this.userId = userId;
        this.cpus = cpus;
        this.ram = ram;
        this.ipSSH = ipSSH;
        this.serverId = serverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameContainer() {
        return nameContainer;
    }

    public void setNameContainer(String nameContainer) {
        this.nameContainer = nameContainer;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getCpus() {
        return cpus;
    }

    public void setCpus(float cpus) {
        this.cpus = cpus;
    }

    public float getRam() {
        return ram;
    }

    public void setRam(float ram) {
        this.ram = ram;
    }

    public String getIpSSH() {
        return ipSSH;
    }

    public void setIpSSH(String ipSSH) {
        this.ipSSH = ipSSH;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    String ipSSH;
    int serverId;
}
