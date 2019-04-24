package Connections;




import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Sender implements Runnable {

    String ip;

    Socket socket;
    String jsonFile;
    JSONObject jsonObject = new JSONObject();

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
            Socket socket = new Socket(ip, 5004);

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
