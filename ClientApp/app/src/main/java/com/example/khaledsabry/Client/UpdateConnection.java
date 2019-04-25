package com.example.khaledsabry.Client;


import com.example.khaledsabry.Client.Processing.MsgEncoder;

public class UpdateConnection implements Runnable {
    @Override
    public void run() {
        while(true)
        {
            try {
                MsgEncoder msgEncoder = new MsgEncoder();
                msgEncoder.updateConnection();
                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
