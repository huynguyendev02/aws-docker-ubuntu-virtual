package com.huicloud.dockerubuntuvirtual.models;

public class Network {
    int id;
    String nameNetwork;
    int userId;
    int serverId;

    public Network(int id, String nameNetwork, int userId, int serverId) {
        this.id = id;
        this.nameNetwork = nameNetwork;
        this.userId = userId;
        this.serverId = serverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameNetwork() {
        return nameNetwork;
    }

    public void setNameNetwork(String nameNetwork) {
        this.nameNetwork = nameNetwork;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
}
