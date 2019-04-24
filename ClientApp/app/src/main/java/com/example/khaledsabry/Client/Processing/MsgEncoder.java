package com.example.khaledsabry.Client.Processing;

import com.example.khaledsabry.Client.Connections.Sender;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MsgEncoder {
    Sender sender;

    public MsgEncoder()
    {
        sender = new Sender();
    }
    private void send(JsonObject jsonObject)
    {
        sender.putJson(jsonObject.toString());

        new Thread(sender).start();
    }


    public void updateConnection()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","update_connection");


        send(jsonObject);
    }

    public void createRoom(String roomName,String roomPassword,String nickName)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","create_room");
        jsonObject.addProperty("room_name",roomName);
        jsonObject.addProperty("room_password",roomPassword);
        jsonObject.addProperty("nick_name",nickName);



        send(jsonObject);
    }

    public void joinRoom(String roomName,String roomPassword,String nickName)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","join_room");
        jsonObject.addProperty("room_name",roomName);
        jsonObject.addProperty("room_password",roomPassword);
        jsonObject.addProperty("nick_name",nickName);

        send(jsonObject);
    }

    public void roomJoinSuccess(int roomId)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_joined");
        jsonObject.addProperty("room_id",roomId);


        send(jsonObject);
    }

    public void roomJoinUnSuccess()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_unsuccess");
        jsonObject.addProperty("msg","change your nickname");


        send(jsonObject);
    }

    public void roomJoinNotFound()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_unsuccess");
        jsonObject.addProperty("msg","incorrect room name/password");


        send(jsonObject);
    }

    public void sendMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","receive_message");
        jsonObject.addProperty("msg",msg);


        send(jsonObject);
    }

    public void deleteMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","delete_message");
        jsonObject.addProperty("msg",msg);


        send(jsonObject);
    }





}
