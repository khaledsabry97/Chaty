package com.example.khaledsabry.Client.Connections;


import android.os.AsyncTask;

import com.example.khaledsabry.Client.Processing.MsgDecoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Reciever extends AsyncTask<Void,JsonObject,JsonObject> {
    int port;

    public Reciever(int port)
    {
        this.port = port;
    }

    @Override
    protected JsonObject doInBackground(Void... voids) {

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Gson gson = new Gson();
                    JsonObject msg = gson.fromJson(String.valueOf(in.readObject()),JsonObject.class);

                    MsgDecoder decoder = new MsgDecoder(msg);
                    decoder.execute();
                    socket.close();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }
        catch (Exception e)
        {

        }





        return null;
    }
}
