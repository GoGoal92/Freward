package gg.googlrewardads;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Go Goal on 4/18/2017.
 */

public class Jsonparser {


    public static String getonestring(String str, String status) {
        JSONObject jo = null;
        String result = "";
        try {
            jo = new JSONObject(str);
            result = jo.getString(status);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }






}
