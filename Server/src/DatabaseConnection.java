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



    public void Post_JSON() {

        try {
            URL url = new URL("http://localhost/explainity/insert_connection.php"); // URL to your application
            Map<String,Object> params = new LinkedHashMap<>();
            //params.put("room_name", "khaled"); // All parameters, also easy
            params.put("room_id", 4);
            params.put("nick_name", "ahmed");
            params.put("ip", "192.168.0.0");
            params.put("last_time_entered",System.currentTimeMillis() );



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
            JSONObject myResponse = new JSONObject(result);
            JSONArray jsonArray = myResponse.getJSONArray("server_response");
            System.out.println("id- "+myResponse.getInt("id"));
            System.out.println("result- "+myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
