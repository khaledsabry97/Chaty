package Data;

import Database.DatabaseQuery;
import Database.DatabaseResultDecoder;

import java.util.HashMap;

/**
 * stores all the client connection to the database
 */
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

    /**
     * check if this ip connected to the server or not
     * @param ip the ip to be checked
     * @return true (connected) - false (not connected)
     */
    public Boolean isConnected(String ip)
    {
        Object found = clients.get(ip);
        if(found == null)
            return false;
        else
            return true;
    }

    /**
     * update the connection of a client
     * @param ip the ip to be updated
     */
    public void updateConnection(String ip)
    {
        clients.put(ip, System.currentTimeMillis());
    }

    /**
     * delete a connection from the server and database
     * @param ip the ip to be deleted
     */
    public void deleteConnection(String ip)
    {
        Boolean result = databaseResultDecoder.getInUpDl(databaseQuery.deleteConnection(ip));
        clients.remove(ip);
    }

    /**
     * call it from the connectedips thread to delete who isn't connected to the server
     */
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
