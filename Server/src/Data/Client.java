package Data;

import Database.DatabaseQuery;
import Database.DatabaseResultDecoder;

import java.util.HashMap;

public class Client {
    DatabaseResultDecoder databaseResultDecoder = new DatabaseResultDecoder();
    DatabaseQuery databaseQuery = new DatabaseQuery();
    private static Client ourInstance = new Client();

    HashMap<String,Long> clients = new HashMap<>();

    public static Client getInstance() {
        return ourInstance;
    }

    private Client() {
    }


    public Boolean isConnected(String ip)
    {
        Object found = clients.get(ip);
        if(found == null)
            return false;
        else
            return true;
    }

    public void updateConnection(String ip)
    {
        clients.put(ip, System.currentTimeMillis());
    }

    public void deleteConnection(String ip)
    {
        Boolean result = databaseResultDecoder.getInUpDl(databaseQuery.deleteConnection(ip));
        clients.remove(ip);
    }

    public void deleteNotConencted()
    {
         Object[] keys =  clients.keySet().toArray();
        for(int i = 0 ;i < keys.length;i++)
        {
            Long time = clients.get(keys[i].toString());
            long diff = System.currentTimeMillis() - time;
            if(diff > 10000)
                deleteConnection(keys[i].toString());

        }
    }

}
