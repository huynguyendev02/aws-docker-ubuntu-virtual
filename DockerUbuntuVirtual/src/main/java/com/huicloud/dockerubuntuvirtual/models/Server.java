package com.huicloud.dockerubuntuvirtual.models;

public class Server {
    int id;
    String ipServer;
    String state;

    public Server(int id, String ipServer, String state, String state1) {
        this.id = id;
        this.ipServer = ipServer;
        this.state = state;
        this.state = state1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Server(int id, String ipServer, String state) {
        this.id = id;
        this.ipServer = ipServer;
        this.state = state;
    }


    public Server(int id, String ipServer) {
        this.id = id;
        this.ipServer = ipServer;
    }

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
}
