public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Thread senderThread = new Thread(new SenderThread());
        Thread recieverThread = new Thread(new RecieveThread());
        senderThread.start();
        recieverThread.start();

    }
}