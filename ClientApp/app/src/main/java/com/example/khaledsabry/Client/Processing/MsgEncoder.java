package com.example.khaledsabry.Client.Processing;

import com.example.khaledsabry.Client.Connections.Sender;
import com.example.khaledsabry.Client.Data.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * to encode all the messages and add the functionality of the message before sending to the server
 */
public class MsgEncoder {
    Sender sender;
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();

    public MsgEncoder() {
        sender = new Sender();
    }

    /**
     * send the message
     *
     * @param jsonObject the message
     */
    private void send(JsonObject jsonObject) {
        sender.putJson(jsonObject.toString());

        new Thread(sender).start();
    }


    /**
     * update connection function
     */
    public void updateConnection() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "update_connection");


        send(jsonObject);
    }

    /**
     * create room request
     *
     * @param roomName
     * @param roomPassword
     * @param nickName
     */
    public void createRoom(String roomName, String roomPassword, String nickName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "create_room");
        jsonObject.addProperty("room_name", roomName);
        jsonObject.addProperty("room_password", roomPassword);
        jsonObject.addProperty("nick_name", nickName);


        send(jsonObject);
    }

    /**
     * join room request
     *
     * @param roomName
     * @param roomPassword
     * @param nickName
     */
    public void joinRoom(String roomName, String roomPassword, String nickName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "join_room");
        jsonObject.addProperty("room_name", roomName);
        jsonObject.addProperty("room_password", roomPassword);
        jsonObject.addProperty("nick_name", nickName);

        send(jsonObject);
    }


    /**
     * send a message to others
     *
     * @param message
     * @param roomId  room i am in
     */
    public void sendMsg(Message message, int roomId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "send_message");
        jsonObject.addProperty("message", gson.toJson(message));
        jsonObject.addProperty("room_id", roomId);

        send(jsonObject);
    }

    /**
     * delete a message
     *
     * @param message to be deleted
     * @param roomId  from others inside this room
     */
    public void deleteMsg(Message message, int roomId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "delete_message");
        jsonObject.addProperty("message", gson.toJson(message));
        jsonObject.addProperty("room_id", roomId);


        send(jsonObject);
    }

    /**
     * log out from the room
     */
    public void logOut() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func", "log_out");

        send(jsonObject);
    }


}
