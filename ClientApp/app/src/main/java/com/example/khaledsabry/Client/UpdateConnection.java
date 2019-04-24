package com.example.khaledsabry.Client;


import com.example.khaledsabry.Client.Processing.MsgEncoder;

public class UpdateConnection implements Runnable {
    //TODO run this class after you sign in
    @Override
    public void run() {
        while(true)
        {
            try {
                //TODO send an update for your connection
                MsgEncoder msgEncoder = new MsgEncoder();
                msgEncoder.updateConnection();
                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
