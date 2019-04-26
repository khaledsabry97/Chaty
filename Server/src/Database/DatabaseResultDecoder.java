package Database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * layer only to make a check and gets the result if there was an error happend
 */
public class DatabaseResultDecoder {

    /**
     * pass to it the json result from insert, update and delete
     * @param jsonObject json result
     * @return true / false
     */
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

    /**
     * pass to it the json result from select
     * @param jsonObject json result
     * @return true / false
     */
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
