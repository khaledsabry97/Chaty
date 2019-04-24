package Data;

import java.io.Serializable;

public class Message implements Serializable {
    String senderNickName;
    String content; //content of the message
    String timeLocal;//to delete by local time
    String timeServer; //show the server time


}
