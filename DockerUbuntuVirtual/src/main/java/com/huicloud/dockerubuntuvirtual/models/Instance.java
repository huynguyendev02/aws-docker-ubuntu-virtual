package com.huicloud.dockerubuntuvirtual.models;

import com.huicloud.dockerubuntuvirtual.services.NetworkService;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;

public class Instance {
    int id;
    String nameInstance;
    double cpus;
    double memory;
    int port;
    int networkId;
    int userId;
    int imageId;
    String state;
    int keyId;
    int terminate;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTerminate() {
        return terminate;
    }

    public void setTerminate(int terminate) {
        this.terminate = terminate;
    }

    public Instance(int id, String nameInstance, double cpus, double memory, int port, int networkId, int userId, int imageId, String state, int keyId) {
        this.id = id;
        this.nameInstance = nameInstance;
        this.cpus = cpus;
        this.memory = memory;
        this.port = port;
        this.networkId = networkId;
        this.userId = userId;
        this.imageId = imageId;
        this.state = state;
        this.keyId = keyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameInstance() {
        return nameInstance;
    }

    public void setNameInstance(String nameInstance) {
        this.nameInstance = nameInstance;
    }

    public double getCpus() {
        return cpus;
    }

    public void setCpus(double cpus) {
        this.cpus = cpus;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public int getport() {
        return port;
    }

    public void setport(int port) {
        this.port = port;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }
    public String serverIp(){
        return ServerServices.findServerById(NetworkService.findNetworkById(this.networkId).serverId).getIpServer();
    }

}
