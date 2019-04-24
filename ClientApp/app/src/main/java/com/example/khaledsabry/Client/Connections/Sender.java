package com.example.khaledsabry.Client.Connections;




import com.example.khaledsabry.Client.Data.Server;

import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Sender implements Runnable {

    String port;
    String jsonFile;
    Server server;

    public Sender()
    {
        server = Server.getInstance();
        this.port = String.valueOf(server.getServerPort());

    }

    public void putJson(String jsonFile)
    {
        this.jsonFile = jsonFile;
    }


    @Override
    public void run() {
        try
        {
            Socket socket = new Socket(server.getServerIp(), Integer.parseInt(port));
            ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
            dos.writeObject(jsonFile);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {

        }
    }
}
