package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Client {
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

    public void deleteNotConencted()
    {
        String[] keys = (String[]) clients.keySet().toArray();
        for(int i = 0 ;i < keys.length;i++)
        {
            Long time = clients.get(keys[i]);
            if(System.currentTimeMillis() - time > 2000)
                clients.remove(keys[i]);

        }
    }

}