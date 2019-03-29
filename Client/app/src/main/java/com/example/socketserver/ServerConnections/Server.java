package com.example.socketserver.ServerConnections;

public class Server {
    static Server instance = null;

    private String RecievePort= "";
    private String ServerIp = "192.168.0.102";
    private String ServerPort = "5000";

    public String getRecievePort() {
        return RecievePort;
    }

    public String getServerIp() {
        return ServerIp;
    }

    public String getServerPort() {
        return ServerPort;
    }

    static Server getInstance(){
        if (instance == null)
            instance = new Server();
        return instance;
    }
}
