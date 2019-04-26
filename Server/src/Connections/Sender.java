package Connections;




import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Sender implements Runnable {

    String ip;
    String jsonFile;

    /**
     * the ip to send to
     * @param ip ip to send to
     */
    public Sender(String ip)
    {
        this.ip = ip;
    }

    /**
     * put the message to send
     * @param jsonFile the message
     */
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
