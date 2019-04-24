package Database;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseConnection {
    String urlFile;
    Map<String,Object> params;


    /**
     * make a connection to make a query
     * @param urlFile the php file to connect to
     * @param params the parameters we pass
     */
    DatabaseConnection(String urlFile, Map<String,Object> params)
    {
        this.urlFile = urlFile;
        this.params = params;
    }


    public JSONObject execute()
    {
        String urlString = "http://localhost/explainity/"+urlFile+".php";

        JSONObject myResponse = null;
        try{
            URL url = new URL(urlString); // URL to your application




            StringBuilder postData = new StringBuilder();
            // POST as urlencoded is basically key-value pairs, as with GET
            // This creates key=value&key=value&... pairs
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            // Convert string to byte array, as it should be sent
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            // Connect, easy
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // Tell server that this is POST and in which format is the data

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result;
            result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            myResponse = new JSONObject(result);

            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }

        return myResponse;
    }


}
