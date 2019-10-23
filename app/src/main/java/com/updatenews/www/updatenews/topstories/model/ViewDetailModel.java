package com.updatenews.www.updatenews.topstories.model;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;
import com.updatenews.www.updatenews.utils.ApplicationClass;
import com.updatenews.www.updatenews.utils.ConstantClass;
import com.updatenews.www.updatenews.volley.CustomStringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewDetailModel implements IViewDetailModel {
    @Override
    public void getDetailListNews(String channelKey, final WSResponse wsResponse) {
        CustomStringRequest stringRequest = new CustomStringRequest(ConstantClass.ARTICLES_END_POINT +
                channelKey + "&apiKey=" + ConstantClass.API_KEY,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<ArticlesModel> newsArrayList = new ArrayList<>();
                            if (response != null) {
                                JSONArray arrayStr = response.getJSONArray("articles");
                                Type listTypeNews = new TypeToken<ArrayList<ArticlesModel>>() {
                                }.getType();
                                newsArrayList = new Gson().fromJson(String.valueOf(arrayStr), listTypeNews);
                                wsResponse.response(newsArrayList,true);

                            }

                        } catch (Exception timeTableExp) {
                            timeTableExp.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(ConstantClass.TIME_OUT, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ApplicationClass.getInstance().addToRequestQueue(stringRequest);
    }
}
