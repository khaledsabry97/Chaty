package Processing;

import Connections.Sender;
import com.google.gson.JsonObject;

/**
 * to encode the message to send it to clients
 */
public class MsgEncoder {
    String recieverIp;
    Sender sender;

    public MsgEncoder(String receiverIp)
    {
        this.recieverIp = receiverIp;
        sender = new Sender(receiverIp);
    }

    /**
     * responsible for sending
     * @param jsonObject the message to send
     */
    private void send(JsonObject jsonObject)
    {
        sender.putJson(jsonObject.toString());
        sender.run();
    }


    /**
     * room has been created successfully
     * @param roomId returns the room id
     */
    public void roomCreatedSuccessfully(int roomId)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_created");
        jsonObject.addProperty("room_id",roomId);


        send(jsonObject);
    }

    /**
     * problem went wrong while creating the room
     */
    public void roomCreatedUnSuccessfully()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_not_created");
        jsonObject.addProperty("msg","there is an unexpected results");


        send(jsonObject);
    }

    /**
     * room was already found in the database
     */
    public void roomCreatedFound()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_created_before");
        jsonObject.addProperty("msg","room has been created before change room name/password");


        send(jsonObject);
    }

    /**
     * access correctly
     * @param roomId returns room id
     */
    public void roomJoinSuccess(int roomId)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_joined");
        jsonObject.addProperty("room_id",roomId);


        send(jsonObject);
    }

    /**
     * something went wrong while trying to join the room
     */
    public void roomJoinUnSuccess()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_unsuccess");
        jsonObject.addProperty("msg","change your nickname");


        send(jsonObject);
    }

    /**
     * the room you entered is not found
     */
    public void roomJoinNotFound()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_not_found");
        jsonObject.addProperty("msg","Incorrect room name/password");


        send(jsonObject);
    }

    /**
     * send the message
     * @param msg the message
     */
    public void sendMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","receive_message");
        jsonObject.addProperty("message",msg);


        send(jsonObject);
    }


    /**
     * delete the message
     * @param msg the message to be delete it
     */
    public void deleteMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","delete_message");
        jsonObject.addProperty("message",msg);


        send(jsonObject);
    }

    /**
     * change the count of the send at sender
     * @param msg the message with the modification
     */
    public void msgSentCount(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","msg_sent_count");
        jsonObject.addProperty("message",msg);


        send(jsonObject);
    }





}
