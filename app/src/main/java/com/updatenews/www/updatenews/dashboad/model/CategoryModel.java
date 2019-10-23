package com.updatenews.www.updatenews.dashboad.model;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.updatenews.www.updatenews.DtosBeans.SourcesModel;
import com.updatenews.www.updatenews.utils.ApplicationClass;
import com.updatenews.www.updatenews.utils.ConstantClass;
import com.updatenews.www.updatenews.volley.CustomStringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoryModel implements ICategoryModel {
    @Override
    public void getListOfChannels(final WSResponse wsResponse) {
        CustomStringRequest stringRequest = new CustomStringRequest(ConstantClass.SOURCES_END_POINT,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             ArrayList<SourcesModel>   sourcesModelArrayList = new ArrayList<>();
                            if (response != null) {
                                JSONArray arrayStr = response.getJSONArray("sources");
                                Type listTypeNews = new TypeToken<ArrayList<SourcesModel>>() {
                                }.getType();
                                sourcesModelArrayList = new Gson().fromJson(String.valueOf(arrayStr), listTypeNews);
                                 wsResponse.response(sourcesModelArrayList,true);
                            }

                        } catch (Exception timeTableExp) {
                            timeTableExp.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                wsResponse.response(null,false);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(ConstantClass.TIME_OUT, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ApplicationClass.getInstance().addToRequestQueue(stringRequest);
    }
}
