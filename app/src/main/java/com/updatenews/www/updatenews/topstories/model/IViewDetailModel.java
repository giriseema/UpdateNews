package com.updatenews.www.updatenews.topstories.model;

import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;

import java.util.ArrayList;

public interface IViewDetailModel {
    void getDetailListNews(String channelKey,WSResponse wsResponse);

    interface WSResponse {
        void response(ArrayList<ArticlesModel>  articlesModels, boolean isError);
    }
}
