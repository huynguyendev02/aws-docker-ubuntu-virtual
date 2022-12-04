package com.huicloud.dockerubuntuvirtual.models;

public class Instance {
    int id;
    String nameInstance;
    float cpus;
    float memory;
    String ipSSH;
    int networkId;
    int userId;
    int imageId;
    String state;
    int keyId;

    public Instance(int id, String nameInstance, float cpus, float memory, String ipSSH, int networkId, int userId, int imageId, String state, int keyId) {
        this.id = id;
        this.nameInstance = nameInstance;
        this.cpus = cpus;
        this.memory = memory;
        this.ipSSH = ipSSH;
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

    public float getCpus() {
        return cpus;
    }

    public void setCpus(float cpus) {
        this.cpus = cpus;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }

    public String getIpSSH() {
        return ipSSH;
    }

    public void setIpSSH(String ipSSH) {
        this.ipSSH = ipSSH;
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
}
