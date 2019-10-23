package com.updatenews.www.updatenews.volley;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


public class VolleyUtils {

    public static boolean getStatus(JSONObject response) {
        try {
            return response.getBoolean("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getMessage(JSONObject response) {
        String responeMessage = null;
        try {
            if (response.has("message")) {
                responeMessage = response.getString("message");
            } else {
                responeMessage = response.getString("tag");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responeMessage;
    }

    public static String parseString(JSONObject response, String key) {
        String responeMessage = null;
        try {
            responeMessage = response.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responeMessage;
    }

    public static String getTag(JSONObject response) {
        String message = null;
        try {
            message = response.getString("tag");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

}
