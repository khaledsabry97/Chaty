import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
public class RecieveThread implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Running.......");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accept Message.......");
                Thread threadAction = new Thread(new Action(socket.getInputStream()));
                threadAction.start();
                socket.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
