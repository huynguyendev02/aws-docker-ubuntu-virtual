package com.huicloud.dockerubuntuvirtual.models;

public class SSHKey {
    int id;
    String nameKey;
    byte[] publicKey;
    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SSHKey(int id, String nameKey, byte[] publicKey, int userId) {
        this.id = id;
        this.nameKey = nameKey;
        this.publicKey = publicKey;
        this.userId = userId;
    }
}
