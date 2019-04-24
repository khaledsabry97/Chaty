package com.example.khaledsabry.Client.Processing;


import android.os.AsyncTask;

import com.google.gson.JsonObject;

public class MsgDecoder extends AsyncTask<Void, Object, Object> {
    JsonObject jsonObject;
    MsgEncoder msgEncoder;

    public MsgDecoder(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        msgEncoder = new MsgEncoder();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            Object result = decoding(jsonObject.get("func").getAsString());
            return result;
        } catch (Exception e) {
            //send there was error happened
            e.printStackTrace();
        }
        return null;
    }

    private Object decoding(String func) {

        if (func == "create_room") {
        } else if (func == "join_room") {
        } else if (func == "send_message") {
        } else if (func == "delete_message") {
        } else if (func == "update_time") {
        } else if (func == "log_out") {
        }
        return null;
    }


    void createRoom()
    {

    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);


    }
}
