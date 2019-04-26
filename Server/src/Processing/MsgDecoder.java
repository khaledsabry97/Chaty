package Processing;

import Data.Message;
import Database.DatabaseQuery;
import Database.DatabaseResultDecoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;

import Data.Client;

import java.util.ArrayList;

/**
 * to decode all the messages from the users
 */
public class MsgDecoder implements Runnable {
    String recieverIp;
    JsonObject jsonObject;
    DatabaseQuery databaseQuery;
    DatabaseResultDecoder databaseResultDecoder;
    MsgEncoder msgEncoder;

    /**
     * make a decoder must have
     * @param jsonObject the json to decode
     * @param recieverIp the ip of the sender to receive later
     */
    public MsgDecoder(JsonObject jsonObject, String recieverIp) {
        this.jsonObject = jsonObject;
        this.recieverIp = recieverIp.substring(1,recieverIp.length());
        databaseQuery = new DatabaseQuery();
        databaseResultDecoder = new DatabaseResultDecoder();
        msgEncoder = new MsgEncoder(this.recieverIp);
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

    /**
     * the decode function
     * @param func the word we have agreed on between the client and server
     */
    private void decoding(String func) {

        if(func.equals("create_room"))
        {
            createRoom();
        }
        else if(func.equals("join_room"))
        {
            joinRoom();

        }
        else if(func.equals("send_message"))
        {
            sendMessage();
        }
        else if(func.equals("delete_message"))
        {
            deleteMessage();
        }
        else if(func.equals("update_connection"))
        {
            updateConnection();
        }
        else if(func.equals("log_out"))
        {
            deleteConnection();
        }

    }


    /**
     * to create new room
     */
    private void createRoom()
    {
        String roomName = jsonObject.get("room_name").getAsString();
        String roomPassword = jsonObject.get("room_password").getAsString();
        String nickName = jsonObject.get("nick_name").getAsString();
        String ip = recieverIp;

        //insert the room
        Boolean result = databaseResultDecoder.getInUpDl(databaseQuery.createRoom(roomName,roomPassword));
        if(result == true)
        {
            //room has been created successfully
            //get its id
            JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getRoomId(roomName,roomPassword));
            if(jsonArray.length() == 0)
            {
                //no room found that's weired
                msgEncoder.roomCreatedUnSuccessfully();
            }
            else
            {
                //get its id
                int roomId = jsonArray.getJSONObject(0).getInt("id");

                //insert me to the conenction to this room
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


    /**
     * to join rome
     */
    private void joinRoom()
    {
        String roomName = jsonObject.get("room_name").getAsString();
        String roomPassword = jsonObject.get("room_password").getAsString();
        String nickName = jsonObject.get("nick_name").getAsString();
        String ip = recieverIp;

        //see if the room in the database
        JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getRoomId(roomName,roomPassword));
        if(jsonArray.length() == 0)
        {
            //no room with that name or the password is incorrect
            msgEncoder.roomJoinNotFound();

        }
        else
        {
            //get its id
            int roomId = jsonArray.getJSONObject(0).getInt("id");
            //insert a connection to it
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


    /**
     * to send to others in the room the message i have wrote
     */
    private void sendMessage()
    {
        Gson gson = new Gson();
        int roomId = jsonObject.get("room_id").getAsInt();
        Message message = gson.fromJson(jsonObject.get("message").getAsString(),Message.class);
        String ip = recieverIp;

        //returns all the ip in the room
       JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getAllIpInRoom(roomId));
       if(jsonArray.length() == 0)
       {
           //something wrong with the query at least i am in the room
       }
       else
       {

           Client client = Client.getInstance();
           ArrayList<String> ips = new ArrayList<>(); //array to store only connected ips
           for(int i =0 ; i < jsonArray.length();i++)
           {
               String ipCurrent = jsonArray.getJSONObject(i).getString("ip");
               if(ipCurrent.equals(ip))
                   continue;
               if(!client.isConnected(ipCurrent))
                   continue;
               ips.add(ipCurrent);
           }
           int count = ips.size();
           message.setSent(count); //send to the sender no. of the clients that have listened to the message you have sent
           message.setServerTime(String.valueOf(System.currentTimeMillis()));
           MsgEncoder msgEncoder = new MsgEncoder(ip);
           msgEncoder.msgSentCount(gson.toJson(message));

           //send to all ips the message
           for(int i =0 ; i < count;i++)
           {
               String ipCurrent = ips.get(i);

               //send the message to that ip
               MsgEncoder msgEncoder1 = new MsgEncoder(ipCurrent);
               msgEncoder1.sendMsg(gson.toJson(message));
           }
       }

    }

    /**
     * delete a message from other clients
     */
    private void deleteMessage()
    {
        Gson gson = new Gson();
        int roomId = jsonObject.get("room_id").getAsInt();
        Message message = gson.fromJson(jsonObject.get("message").getAsString(),Message.class);
        String ip = recieverIp;

        //get all ips inside the room
        JSONArray jsonArray = databaseResultDecoder.getSelect(databaseQuery.getAllIpInRoom(roomId));
        if(jsonArray.length() == 0)
        {
            //something wrong with the query at least i am in the room
        }
        else
        {

            Client client = Client.getInstance();
            ArrayList<String> ips = new ArrayList<>();
            //get only the connected ips
            for(int i =0 ; i < jsonArray.length();i++)
            {
                String ipCurrent = jsonArray.getJSONObject(i).getString("ip");
                if(ipCurrent.equals(ip))
                    continue;
                if(!client.isConnected(ipCurrent))
                    continue;
                ips.add(ipCurrent);
            }
            int count = ips.size();
            //send to them the delete request
            for(int i =0 ; i < count;i++)
            {
                String ipCurrent = ips.get(i);

                //send the message to that ip
                MsgEncoder msgEncoder1 = new MsgEncoder(ipCurrent);
                msgEncoder1.deleteMsg(gson.toJson(message));
            }
        }

    }

    /**
     * to update the connection so the client still be connected
     */
    private void updateConnection()
    {
        Client client = Client.getInstance();
        client.updateConnection(recieverIp);
    }


    /**
     * delete a connection from the database and server
     */
    private void deleteConnection() {

        //delete connection from database
        String ip = recieverIp;

        Client client = Client.getInstance();
        client.deleteConnection(ip);

    }
}
