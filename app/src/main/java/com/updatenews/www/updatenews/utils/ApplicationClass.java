package com.updatenews.www.updatenews.utils;

import androidx.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.updatenews.www.updatenews.preferences.AppPreferences;

import java.io.Serializable;


public class ApplicationClass extends MultiDexApplication implements
        Serializable {
    public static final String TAG = ApplicationClass.class.getSimpleName();
    private static final long serialVersionUID = 7085840640792312830L;
    private static ApplicationClass mInstance;
    private AppPreferences mPref;
    private RequestQueue mRequestQueue;

    public static synchronized ApplicationClass getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mPref = AppPreferences.getAppPreferences(getApplicationContext());
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}




