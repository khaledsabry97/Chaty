package com.example.khaledsabry.Client.ServerConnections;

import java.util.ArrayList;
import java.util.Random;

public class Server {
    static Server instance = null;

    private String RecievePort= "";
    private String ServerIp = "192.168.0.102";
    private ArrayList<String> ServerPorts = new ArrayList<>();

    public String getRecievePort() {
        return RecievePort;
    }

    public String getServerIp() {
        return ServerIp;
    }

    public String getServerPort() {
        Random random = new Random();
        int no = random.nextInt(ServerPorts.size());
        return ServerPorts.get(no);
    }

    private Server ()
    {
        ServerPorts.add("5000");
        ServerPorts.add("5002");
        ServerPorts.add("5004");
        ServerPorts.add("5006");
        ServerPorts.add("5008");
        ServerPorts.add("5010");
    }

    static Server getInstance(){
        if (instance == null)
            instance = new Server();
        return instance;
    }
}
