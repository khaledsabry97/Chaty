import org.json.JSONObject;
import org.json.JSONString;

import java.io.*;

public class Action implements Runnable {
    String msg;
    Action(String inputStream)
    {
        this.msg = inputStream;
    }

    @Override
    public void run() {

        JSONObject json = new JSONObject(msg);
        System.out.println(json.getString("method"));

    }


    void TakeAction()
    {

    }
}
