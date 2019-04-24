package com.example.khaledsabry.Client.Data;

import java.util.ArrayList;
import java.util.Random;

public class Server {
    static Server instance = null;

    private String RecievePort= "";
    private String ServerIp = "192.168.0.103";
    private ArrayList<Integer> ServerPorts = new ArrayList<>();

    public String getRecievePort() {
        return RecievePort;
    }

    public String getServerIp() {
        return ServerIp;
    }

    public Integer getServerPort() {
        Random random = new Random();
        int no = random.nextInt(ServerPorts.size());
        return ServerPorts.get(no);
    }

    private Server ()
    {
        ServerPorts.add(10000);
        ServerPorts.add(10002);
        ServerPorts.add(10004);
    }

    public static Server getInstance(){
        if (instance == null)
            instance = new Server();
        return instance;
    }
}
