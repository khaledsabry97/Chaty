package com.example.khaledsabry.Client.Data;

/**
 * to save current room name and room id and nickname you are using
 */
public class Data {
    private static final Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
    }


    String roomName;
    int roomId;
    String nickName;


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * clear all the data when you log out of the room
     */
    public void clearData()
    {
        nickName = "";
        roomId = -1;
        roomName = "";

    }
}
