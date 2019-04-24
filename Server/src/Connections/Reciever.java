package Connections;


import Processing.MsgDecoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Reciever implements Runnable {
    String port;

    public Reciever(String port)
    {
        this.port = port;
    }
    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
                while (true) {
                    try {
                        System.out.println("Running On Port"+port.toString()+".......");
                        Socket socket = serverSocket.accept();
                        System.out.println("Accept Data.Message On Port"+port.toString()+".......");
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        Gson gson = new Gson();
                        JsonObject msg = gson.fromJson(String.valueOf(in.readObject()),JsonObject.class);

                        MsgDecoder decoder = new MsgDecoder(msg,socket.getInetAddress().toString());
                        Thread thread = new Thread(decoder);
                        thread.start();
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





    }





}
