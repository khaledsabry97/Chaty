import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
public class RecieveThread implements Runnable {
    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(5000);
                while (true) {
                    System.out.println("Running.......");
                    Socket socket = serverSocket.accept();
                    System.out.println("Accept Message.......");
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    System.out.println(socket.getInetAddress());
                    System.out.println(socket.getPort());
                    Thread threadAction = new Thread(new Action(in.readUTF()));
                    threadAction.start();
                    socket.close();

                }
        } catch (IOException e) {
            e.printStackTrace();

        }
        catch (Exception e)
        {

        }





    }





}
