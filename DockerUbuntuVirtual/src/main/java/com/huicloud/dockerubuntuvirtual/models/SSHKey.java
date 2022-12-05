package com.huicloud.dockerubuntuvirtual.models;

import java.security.KeyPairGenerator;
import java.security.PublicKey;

public class SSHKey {
    int id;
    String nameKey;
    int userId;

    public SSHKey(int id, String nameKey, int userId) {
        this.id = id;
        this.nameKey = nameKey;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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


}

