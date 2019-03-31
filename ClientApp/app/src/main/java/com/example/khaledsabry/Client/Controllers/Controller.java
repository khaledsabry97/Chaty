package com.example.khaledsabry.Client.Controllers;


import com.example.khaledsabry.Client.ServerConnections.Sender;

import org.json.JSONObject;

public class Controller {

    Sender sender;
    String id; //identification id the server sends to you when you sign in this is what you will use when you connect to the server


    /**
     * this 'll send a json object to the server by making a new thread
     * @param jsonObject the sending msg
     */
    void send(JSONObject jsonObject)
    {
        new Sender().execute(jsonObject);
    }
}
