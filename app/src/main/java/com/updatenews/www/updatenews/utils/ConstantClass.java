package com.updatenews.www.updatenews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.google.firebase.auth.FirebaseUser;
import com.updatenews.www.updatenews.DtosBeans.SourcesModel;

import java.util.ArrayList;

public class ConstantClass {
    public final static String API_KEY = "292ba5bfef3a4cae9beb86bd36b086b5";
    public final static String ARTICLES_END_POINT = "https://newsapi.org/v1/articles?source=";
    public final static String SOURCES_END_POINT = "https://newsapi.org/v1/sources";

    public static final int TIME_OUT = 90000;
    public static final int METHOD_TYPE_POST = Request.Method.POST;
    public static final int METHOD_TYPE_GET = Request.Method.GET;

    public static final String APP_URL = "https://play.google.com/store/apps/details?id=com.seema.giri.thecurrentnews";
    public static final String CONTACTS = "contacts/";
    public static final String SOURCE_MODEL = "SOURCE_MODEL";
    public static FirebaseUser USER_DETAIL = null;
    public static ArrayList<SourcesModel> golobalSourcesModelsList;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager con_manager = (ConnectivityManager)(context)
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net_info = con_manager.getActiveNetworkInfo();
        return net_info != null;

    }
    public static ArrayList<SourcesModel> resetFilter(ArrayList<SourcesModel> oldSourceList, String resetKey) {
        ArrayList<SourcesModel> resetList = new ArrayList<>();
        for (SourcesModel sourcesModel : oldSourceList) {
            if (sourcesModel.getLanguage().equalsIgnoreCase(resetKey)) {
                resetList.add(sourcesModel);
            }
        }
        return resetList;
    }

    public static ArrayList<SourcesModel> resetChannelList(ArrayList<SourcesModel> oldSourceList, String resetKey) {
        ArrayList<SourcesModel> resetList = new ArrayList<>();
        for (SourcesModel sourcesModel : oldSourceList) {
            if (sourcesModel.getCategory().equalsIgnoreCase(resetKey)) {
                resetList.add(sourcesModel);
            }
        }
        return resetList;
    }
}
