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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class CustomStringRequest extends Request<JSONObject> {

    private Listener<JSONObject> listener;
    private String methodName;
    private Context context;
    private long lastRequestStartTime;


    public CustomStringRequest(String url,
                               Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;

    }


    public CustomStringRequest(Context context,
                               int method, String url, String methodName,
                               Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.methodName = methodName;
        this.context = context;
    }




    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(CustomStringRequest.class.getSimpleName() + " Response Error:", e.toString());
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            Log.e(CustomStringRequest.class.getSimpleName() + " Response Error:", je.toString());
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        Log.i(CustomStringRequest.class.getSimpleName() + " Response:", response.toString());
        listener.onResponse(response);
    }
}