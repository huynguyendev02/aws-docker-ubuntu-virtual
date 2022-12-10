package com.huicloud.dockerubuntuvirtual.models;


import com.huicloud.dockerubuntuvirtual.services.ServerServices;

public class Snapshot {
    int id;
    String nameSnapshot;
    int userId;
    int instanceId;
    int serverId;
    String createdAt;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Snapshot(int id, String nameSnapshot, int userId, int instanceId, int serverId, String createdAt) {
        this.id = id;
        this.nameSnapshot = nameSnapshot;
        this.userId = userId;
        this.instanceId = instanceId;
        this.serverId = serverId;
        this.createdAt = createdAt;
    }

    public Snapshot(int id, String nameSnapshot, int userId, int instanceId) {
        this.id = id;
        this.nameSnapshot = nameSnapshot;
        this.userId = userId;
        this.instanceId = instanceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSnapshot() {
        return nameSnapshot;
    }

    public void setNameSnapshot(String nameSnapshot) {
        this.nameSnapshot = nameSnapshot;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getinstanceId() {
        return instanceId;
    }

    public void setinstanceId(int instanceId) {
        this.instanceId = instanceId;
    }
    public String getIPServer(){
        return ServerServices.findServerById(this.serverId).ipServer;
    }
}
