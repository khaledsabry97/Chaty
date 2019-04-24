package Connections;




import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Sender implements Runnable {

    String ip;
    String jsonFile;

    public Sender(String ip)
    {
        this.ip = ip;
    }

    public void putJson(String jsonFile)
    {
        this.jsonFile = jsonFile;
    }


    @Override
    public void run() {
        try
        {
            Socket socket = new Socket(ip, 5000);

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
