package Database;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseQuery {
    private final String create_room = "insert_room";
    private final String join_room = "insert_connection";
    private final String get_room_id = "select_room_name";
    private final String update_connection_time = "update_connection_time";
    private final String check_nick_name_available = "select_connection_nick_name";
    private final String get_all_ip_in_room = "select_all_connections_in_room";
    private final String delete_connection = "delete_Connection";


    /**
     * create a new room when user make create new Room Request
     * @param roomName the nickname of the room the user pass
     * @param password the password that username has assigned
     * @return if successfuly created return True if not return False
     */
    public JSONObject createRoom(String roomName,String password)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_name", roomName);
        params.put("password", password);

        return execute(create_room,params);

    }

    /**
     * when user enter room name and his nick name and enter the room request
     * @param roomId get from function "getRoomId" the id of the room that user entered its name
     * @param nickName the user nickname he entered
     * @param ip the ip of the user
     * @return if successfuly created return True if not return False
     */
    public JSONObject joinRoom(int roomId, String nickName, String ip)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);
        params.put("nick_name", nickName);
        params.put("ip", ip);
        params.put("last_time_entered",System.currentTimeMillis());

        return execute(join_room,params);

    }

    /**
     * when  you enter the "join room request" you send the neme of the room
     * @param roomName name of the room to get its id to use it later in the "joinRoom" function
     * @return room id
     */
    public JSONObject getRoomId(String roomName,String roomPassword)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_name", roomName);
        params.put("room_password",roomPassword);

        return execute(get_room_id,params);

    }

    /**
     * update the time connection of the user
     * @param roomId room id that user has signed in to
     * @param nickName the nickname of the user
     * @param ip the ip of the user
     * @return if successfuly created return True if not return False
     */
    public JSONObject updateConnectionTime(int roomId,String nickName,String
                                           ip)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);
        params.put("nick_name", nickName);
        params.put("ip", ip);
        params.put("last_time_entered",System.currentTimeMillis());

        return execute(update_connection_time,params);

    }

    /**
     * select all the ips in the database to the room
     * @param roomId put room id
     * @return array of all ips
     */
    public JSONObject getAllIpInRoom(int roomId)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);

        return execute(get_all_ip_in_room,params);

    }

    public JSONObject deleteConnection(String ip)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("ip", ip);

        return execute(delete_connection,params);

    }

    @Deprecated
    public JSONObject checkNickNameAvailable(int roomId,String nickName)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);
        params.put("nick_name", nickName);

        return execute(check_nick_name_available,params);

    }




    private JSONObject execute(String url,Map<String,Object> params)
    {
        return new DatabaseConnection(url,params).execute();
    }
}
