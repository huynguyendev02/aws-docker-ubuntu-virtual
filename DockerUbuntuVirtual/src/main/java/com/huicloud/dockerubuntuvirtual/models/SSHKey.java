package com.huicloud.dockerubuntuvirtual.models;

import java.security.KeyPairGenerator;
import java.security.PublicKey;

public class SSHKey {
    int id;
    String nameKey;
    PublicKey publicKey;

    public SSHKey(int id, String nameKey, PublicKey publicKey) {
        this.id = id;
        this.nameKey = nameKey;
        this.publicKey = publicKey;
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

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}

