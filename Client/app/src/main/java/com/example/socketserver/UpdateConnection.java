package com.example.socketserver;


public class UpdateConnection implements Runnable {
    //TODO run this class after you sign in
    @Override
    public void run() {
        while(true)
        {
            try {
                wait(60000);
                //TODO send an update for your connection
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
