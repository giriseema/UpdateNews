package com.updatenews.www.updatenews.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CustomBodyRequest extends Request<JSONObject> {
    private Response.Listener<JSONObject> listener;
    private String methodName;
    private Context context;
    private long lastRequestStartTime;
    private String mJsonBody;

    public CustomBodyRequest(Context context,
                             int method, String url, String mJsonBody, String methodName,
                             Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.methodName = methodName;
        this.context = context;
        this.mJsonBody = mJsonBody;

    }




    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            Log.i(CustomBodyRequest.class.getSimpleName(), mJsonBody);
            return mJsonBody == null ? null : mJsonBody.getBytes("utf-8");
        } catch (Exception e) {
            VolleyLog.e(CustomBodyRequest.class.getSimpleName(), e.toString());
            return null;
        }
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
        Log.i(CustomBodyRequest.class.getSimpleName() + " Response:", response.toString());
        listener.onResponse(response);
    }
}
