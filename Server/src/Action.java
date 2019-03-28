import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Action implements Runnable {
    InputStream inputStream;
    Action(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            JSONObject jsonObject = convertToJson(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    JSONObject convertToJson(InputStream dataInputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = "";
        StringBuilder responseStrBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {

            responseStrBuilder.append(line);
        }
        dataInputStream.close();
        JSONObject result = new JSONObject(responseStrBuilder.toString());
        return result;
    }


    void TakeAction()
    {

    }
}
