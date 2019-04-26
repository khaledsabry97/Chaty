package Processing;

import Data.Client;

/**
 * to make constant check if there is a user diconnected from a server
 */
public class ConnectedIps implements Runnable {
    @Override
    public void run() {
        Client client = Client.getInstance();
        while(true)
        {
            try
            {
                client.deleteNotConencted(); //delete all who not conencted
                Thread.sleep(2000); // sleep the thread for 2 sec
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
