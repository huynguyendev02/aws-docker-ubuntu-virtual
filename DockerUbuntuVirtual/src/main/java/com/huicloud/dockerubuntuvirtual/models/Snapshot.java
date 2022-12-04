package com.huicloud.dockerubuntuvirtual.models;

public class Snapshot {
    int id;
    String nameSnapshot;
    int userId;
    int imageId;

    public Snapshot(int id, String nameSnapshot, int userId, int imageId) {
        this.id = id;
        this.nameSnapshot = nameSnapshot;
        this.userId = userId;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
