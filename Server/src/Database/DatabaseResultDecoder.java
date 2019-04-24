package Database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseResultDecoder {

    public Boolean getInUpDl(JSONObject jsonObject)
    {
        try{
            Boolean result =jsonObject.getBoolean("server_response");
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public JSONArray getSelect(JSONObject jsonObject)
    {
        try{
            JSONArray result =jsonObject.getJSONArray("server_response");
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new JSONArray();
    }

}
