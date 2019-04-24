import Connections.Reciever;
import Database.DatabaseConnection;
import Database.DatabaseQuery;
import Processing.ConnectedIps;

public class Main {

    public static void main(String[] args) {
        Thread coonectedThread = new Thread(new ConnectedIps());
        Thread recieverThread1 = new Thread(new Reciever(String.valueOf(10000)));
        Thread recieverThread2 = new Thread(new Reciever(String.valueOf(10002)));
        Thread recieverThread3 = new Thread(new Reciever(String.valueOf(10004)));

        recieverThread1.start();
        recieverThread2.start();
        recieverThread3.start();
        coonectedThread.start();


    }
}
