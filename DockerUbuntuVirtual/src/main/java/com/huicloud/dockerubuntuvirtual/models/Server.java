package com.huicloud.dockerubuntuvirtual.models;

public class Server {
    int id;
    String ipServer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public Server() {
    }

    public Server(int id, String ipServer) {
        this.id = id;
        this.ipServer = ipServer;
    }
}
