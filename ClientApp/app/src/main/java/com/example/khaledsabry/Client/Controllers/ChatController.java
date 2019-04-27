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


    /**
     * to set current context of the chat fragment
     * @param chatFragment current chat fragment
     */
    public void setChatFragment(ChatFragment chatFragment)
    {
        this.chatFragment = chatFragment;
    }

    /**
     * send message to other and add to yourself your message
     * @param content
     */
    public void sendRequest(String content)
    {
        Message message = new Message();
        message.setSent(0);
        message.setContent(content);
        message.setNickName(data.getNickName());
        message.setLocalTime(String.valueOf(System.currentTimeMillis()));
        message.setServerTime(String.valueOf(System.currentTimeMillis()));

        chatFragment.addMessage(message);
        //then send it to the server
        msgEncoder.sendMsg(message,data.getRoomId());

    }

    /**
     * log out from channel
     */
    public void logOut()
    {
        msgEncoder.logOut();
        data.clearData();
        //then send to the server to delete you from the room

    }

    /**
     * delete message of others
     * @param message message that you must delete
     */
    public void deleteMessageResponse(Message message)
    {
        chatFragment.deleteMessage(message);

    }

    /**
     * when you want to delete one of your messages
     * @param message the message you want to delete
     */
    public void deleteMessageRequest(Message message)
    {
        chatFragment.deleteMessage(message);
        //then let the server delete all the messages
        msgEncoder.deleteMsg(message,data.getRoomId());
    }

    /**
     * update no. of the sent message to users
     * @param message the message to update its count
     */
    public void updateSentCount(Message message)
    {
        chatFragment.updateSentCount(message);
    }

    /**
     * add message received from others and yours
     * @param message message to add
     */
    public void addMessage(Message message)
    {
        chatFragment.addMessage(message);
    }
}
