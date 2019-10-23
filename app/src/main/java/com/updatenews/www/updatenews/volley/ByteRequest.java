package com.updatenews.www.updatenews.volley;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class ByteRequest extends Request<byte[]> {

    private Response.Listener<byte[]> listener;
    private Map<String, String> params;


    public ByteRequest(int method, String url, Map<String, String> params,
                       Response.Listener<byte[]> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params;
    }


    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(response.data,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception je) {
            Log.e(CustomJsonRequest.class.getSimpleName() + " Response Error:", je.toString());
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(byte[] response) {
        // TODO Auto-generated method stub
        Log.i(CustomJsonRequest.class.getSimpleName() + " Response:", response.toString());
        listener.onResponse(response);
    }
}