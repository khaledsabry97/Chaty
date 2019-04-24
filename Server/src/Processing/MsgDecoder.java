package Processing;

import Database.DatabaseQuery;
import Database.DatabaseResultDecoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import Data.Message;

public class MsgDecoder implements Runnable {
    String recieverIp;
    JsonObject jsonObject;
    DatabaseQuery databaseQuery;
    DatabaseResultDecoder databaseResultDecoder;
    MsgEncoder msgEncoder;

    public MsgDecoder(JsonObject jsonObject, String recieverIp) {
        this.jsonObject = jsonObject;
        this.recieverIp = recieverIp.substring(1,recieverIp.length());
        databaseQuery = new DatabaseQuery();
        databaseResultDecoder = new DatabaseResultDecoder();
        msgEncoder = new MsgEncoder(recieverIp);
    }

    @Override
    public void run() {
        try
        {
            decoding(jsonObject.get("func").getAsString());
        }
        catch (Exception e)
        {
            //send there was error happened
            e.printStackTrace();
        }

    }

    private void decoding(String func) {

        if(func == "create_room")
        {
            createRoom();
        }
        else if(func == "join_room")
        {
            joinRoom();

        }
        else if(func == "send_message")
        {
            sendMessage();
        }
        else if(func == "delete_message")
        {
            deleteMessage();
        }
        else if(func == "update_time")
        {
            updateConnection();
        }

    }



    private void createRoom()
    {
        String roomName = jsonObject.get("room_name").getAsString();
        String roomPassword = jsonObject.get("room_password").getAsString();
        String nickName = jsonObject.get("nick_name").getAsString();
        String ip = recieverIp;

        Boolean result = databaseResultDecoder.getInUpDl(databaseQuery.createRoom(roomName,roomPassword));
        if(result == true)
        {
            //room has been created successfully
            JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getRoomId(roomName,roomPassword));
            if(jsonArray.length() == 0)
            {
                //no room found that's weired
                msgEncoder.roomCreatedUnSuccessfully();
            }
            else
            {
                int roomId = jsonArray.getJSONObject(0).getInt("room_id");
                Boolean result2 = databaseResultDecoder.getInUpDl(databaseQuery.joinRoom(roomId,nickName,ip));
                if(result2 == true)
                {
                    //the user has successfully entered the room
                    msgEncoder.roomCreatedSuccessfully(roomId);

                }
                else
                {
                    //that's weired no one created this room before
                    msgEncoder.roomCreatedUnSuccessfully();
                }

            }

        }
        else
        {
            //room was found change the room name
            msgEncoder.roomCreatedFound();
        }
    }


    private void joinRoom()
    {
        String roomName = jsonObject.get("room_name").getAsString();
        String roomPassword = jsonObject.get("room_password").getAsString();
        String nickName = jsonObject.get("nick_name").getAsString();
        String ip = recieverIp;

        JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getRoomId(roomName,roomPassword));
        if(jsonArray.length() == 0)
        {
            //no room with that name or the password is incorrect
            msgEncoder.roomJoinNotFound();

        }
        else
        {
            int roomId = jsonArray.getJSONObject(0).getInt("room_id");
            Boolean result = databaseResultDecoder.getInUpDl(databaseQuery.joinRoom(roomId,nickName,ip));
            if(result == true)
            {
                //you are now in the room
                //send success message
                msgEncoder.roomJoinSuccess(roomId);
            }
            else
            {
                //there is another one with the same nick name
                msgEncoder.roomJoinUnSuccess();
            }

        }


    }



    private void sendMessage()
    {
        Gson gson = new Gson();
        int roomId = jsonObject.get("room_id").getAsInt();
        Message message = gson.fromJson(jsonObject.get("message").getAsString(),Message.class);
        String ip = recieverIp;

       JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getAllIpInRoom(roomId));

       if(jsonArray.length() == 0)
       {
           //something wrong with the query at least i am in the room
       }
       else
       {

           for(int i =0 ; i < jsonArray.length();i++)
           {
               String ipCurrent = jsonArray.getJSONObject(i).getString("ip");
               if(ipCurrent == ip)
                   continue;
               //send the message to that ip
               MsgEncoder msgEncoder = new MsgEncoder(ipCurrent);
               msgEncoder.sendMsg(gson.toJson(message));
           }
       }

    }

    private void deleteMessage()
    {
        Gson gson = new Gson();
        int roomId = jsonObject.get("room_id").getAsInt();
        Message message = gson.fromJson(jsonObject.get("message").getAsString(),Message.class);
        String ip = recieverIp;

        JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getAllIpInRoom(roomId));

        if(jsonArray.length() == 0)
        {
            //something wrong with the query at least i am in the room
        }
        else
        {

            for(int i =0 ; i < jsonArray.length();i++)
            {
                String ipCurrent = jsonArray.getJSONObject(i).getString("ip");
                if(ipCurrent == ip)
                    continue;
                //send the message to that ip
                MsgEncoder msgEncoder = new MsgEncoder(ipCurrent);
                msgEncoder.deleteMsg(gson.toJson(message));
            }
        }

    }

    private void updateConnection()
    {
        Data.Client client = Data.Client.get
    }

}
