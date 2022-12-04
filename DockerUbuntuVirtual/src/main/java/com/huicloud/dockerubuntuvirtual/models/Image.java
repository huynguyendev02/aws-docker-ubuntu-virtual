package com.huicloud.dockerubuntuvirtual.models;

public class Image {
    int id;
    String nameImage;

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
