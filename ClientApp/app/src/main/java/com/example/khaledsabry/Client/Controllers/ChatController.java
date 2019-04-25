package com.example.khaledsabry.Client.Controllers;

import com.example.khaledsabry.Client.Data.Data;
import com.example.khaledsabry.Client.Data.Message;
import com.example.khaledsabry.Client.Fragments.ChatFragment;

public class ChatController extends Controller {
    Data data = Data.getInstance();
    private static final ChatController ourInstance = new ChatController();

    public static ChatController getInstance() {
        return ourInstance;
    }

    private ChatController() {
    }

    ChatFragment chatFragment;


    public void setChatFragment(ChatFragment chatFragment)
    {
        this.chatFragment = chatFragment;
    }

    public void sendRequest(String content)
    {
        Message message = new Message();
        message.setSent(0);
        message.setContent(content);
        message.setNickName(data.getNickName());
        message.setLocalTime(String.valueOf(System.currentTimeMillis()));

        chatFragment.addMessage(message);
        //then send it to the server
        msgEncoder.sendMsg(message,data.getRoomId());

    }

    public void logOut()
    {
        msgEncoder.logOut();
        data.clearData();
        //then send to the server to delete you from the room

    }

    public void deleteMessageResponse(Message message)
    {
        chatFragment.deleteMessage(message);

    }


    public void deleteMessageRequest(Message message)
    {
        chatFragment.deleteMessage(message);
        //then let the server delete all the messages
        msgEncoder.deleteMsg(message,data.getRoomId());
    }

    public void updateSentCount(Message message)
    {
        chatFragment.updateSentCount(message);
    }

    public void addMessage(Message message)
    {
        chatFragment.addMessage(message);
    }
}
