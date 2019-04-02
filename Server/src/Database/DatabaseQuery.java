package Database;

import Interfaces.OnDatabaseReceived;
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





    public JSONObject createRoom(String roomName,String password)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_name", roomName);
        params.put("password", password);

        return execute(create_room,params);

    }

    public JSONObject joinRoom(int roomId, String nickName, String ip)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);
        params.put("nick_name", nickName);
        params.put("ip", ip);
        params.put("last_time_entered",System.currentTimeMillis());

        return execute(join_room,params);

    }


    public JSONObject getRoomId(String roomName)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_name", roomName);

        return execute(get_room_id,params);

    }
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

    public JSONObject getAllIpInRoom(int roomId)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("room_id", roomId);

        return execute(get_all_ip_in_room,params);

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
