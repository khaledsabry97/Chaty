package com.example.khaledsabry.Client.Controllers;

import org.json.JSONException;
import org.json.JSONObject;

public class ActionController extends Controller {
    JSONObject jsonObject;
    public ActionController(String jsonObjectStr) {
        try {
            this.jsonObject = new JSONObject(jsonObjectStr);
            work(jsonObject.getString("function"));


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    /**
     * to determine what the received msg refer to
     * @param funct the functionality the server wants me to do
     */
    void work(String funct)
    {
        switch (funct)
        {
            case "SignIn":
                break;
            case "SignUp":
                break;
            case "ProfileImage":
                break;
            case "RoomInviteLink":
                break;
                default:
        }
    }


}
