package com.example.khaledsabry.Client.Processing;


import com.example.khaledsabry.Client.Controllers.SignInUpControlller;
import com.google.gson.JsonObject;

public class MsgDecoder implements Runnable {
    JsonObject jsonObject;
    MsgEncoder msgEncoder;
    SignInUpControlller signInUpControlller = SignInUpControlller.getInstance();

    public MsgDecoder(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        msgEncoder = new MsgEncoder();
    }

    @Override
    public void run() {
        decoding(jsonObject.get("func").getAsString());
    }


    private Object decoding(String func) {

        if (func.equals("room_created")) {
            roomCreatedSuccessfully();
        } else if (func.equals("room_not_created")) {
            roomCreatedUnSuccessfully();
        } else if (func.equals("room_created_before")) {
            roomCreatedFound();
        } else if (func.equals("room_joined")) {
            roomJoinSuccess();
        } else if (func.equals("room_join_unsuccess")) {
            roomJoinUnSuccess();
        } else if (func.equals("room_join_not_found")) {
            roomJoinNotFound();
        }

        return null;
    }


    private void roomCreatedSuccessfully() {
        Integer roomId = jsonObject.get("room_id").getAsInt();

        signInUpControlller.roomCreatedSuccessfully(roomId);
    }

    private void roomCreatedUnSuccessfully() {
        String msg = jsonObject.get("msg").getAsString();
        signInUpControlller.roomCreatedUnSuccessfully(msg);

    }

    private void roomCreatedFound() {
        String msg = jsonObject.get("msg").getAsString();
        signInUpControlller.roomCreatedFound(msg);
    }

    private void roomJoinSuccess() {
        Integer roomId = jsonObject.get("room_id").getAsInt();

        signInUpControlller.roomJoinSuccess(roomId);
    }

    private void roomJoinUnSuccess() {
        String msg = jsonObject.get("msg").getAsString();
        signInUpControlller.roomJoinUnSuccess(msg);
    }

    private void roomJoinNotFound() {
        String msg = jsonObject.get("msg").getAsString();
        signInUpControlller.roomJoinNotFound(msg);

    }


}
