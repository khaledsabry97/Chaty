package Processing;

import Connections.Sender;
import Data.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MsgEncoder {
    String recieverIp;
    Sender sender;

    public MsgEncoder(String receiverIp)
    {
        this.recieverIp = receiverIp;
        sender = new Sender(receiverIp);
    }
    private void send(JsonObject jsonObject)
    {
        sender.putJson(jsonObject.toString());
        sender.run();
    }


    public void roomCreatedSuccessfully(int roomId)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_created");
        jsonObject.addProperty("room_id",roomId);


        send(jsonObject);
    }

    public void roomCreatedUnSuccessfully()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_not_created");
        jsonObject.addProperty("msg","there is an unexpected results");


        send(jsonObject);
    }

    public void roomCreatedFound()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_created_before");
        jsonObject.addProperty("msg","room has been created before change room name");


        send(jsonObject);
    }

    public void roomJoinSuccess(int roomId)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_joined");
        jsonObject.addProperty("room_id",roomId);


        send(jsonObject);
    }

    public void roomJoinUnSuccess()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_unsuccess");
        jsonObject.addProperty("msg","change your nickname");


        send(jsonObject);
    }

    public void roomJoinNotFound()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","room_join_unsuccess");
        jsonObject.addProperty("msg","incorrect room name/password");


        send(jsonObject);
    }

    public void sendMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","receive_message");
        jsonObject.addProperty("msg",msg);


        send(jsonObject);
    }

    public void deleteMsg(String msg)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("func","delete_message");
        jsonObject.addProperty("msg",msg);


        send(jsonObject);
    }





}
