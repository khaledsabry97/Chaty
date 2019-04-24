package Processing;

import Data.Client;

public class ConnectedIps implements Runnable {
    @Override
    public void run() {
        Client client = Client.getInstance();
        while(true)
        {
            try
            {
                client.deleteNotConencted();
                Thread.sleep(10000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
