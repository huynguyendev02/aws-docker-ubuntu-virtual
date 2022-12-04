package com.huicloud.dockerubuntuvirtual.models;

import java.security.KeyPairGenerator;
import java.security.PublicKey;

public class SSHKey {
    int id;
    String nameKey;

    public SSHKey(int id, String nameKey) {
        this.id = id;
        this.nameKey = nameKey;
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

