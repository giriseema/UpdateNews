package com.updatenews.www.updatenews.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.updatenews.www.updatenews.preferences.AppPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class CustomJsonRequest extends Request<JSONObject> {

    private Listener<JSONObject> listener;
    private Map<String, String> params;
    private String methodName;
    private Context context;
    private long lastRequestStartTime;

    private AppPreferences mPref;


    public CustomJsonRequest(String url, Map<String, String> params,
                             Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }


    public CustomJsonRequest(Context context,
                             int method, String url, Map<String, String> params, String methodName,
                             Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.methodName = methodName;
        this.context = context;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        if (params != null) {
            JSONObject obj = new JSONObject(params);
            Log.i(CustomJsonRequest.class.getSimpleName() + " Request:", obj.toString());
        }
        return params;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(CustomJsonRequest.class.getSimpleName() + " Response Error:", e.toString());
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            Log.e(CustomJsonRequest.class.getSimpleName() + " Response Error:", je.toString());
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        Log.i(CustomJsonRequest.class.getSimpleName() + " Response:", response.toString());
        listener.onResponse(response);
    }
}