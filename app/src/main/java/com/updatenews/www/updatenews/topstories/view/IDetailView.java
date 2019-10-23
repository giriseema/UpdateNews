package com.updatenews.www.updatenews.topstories.view;

import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;

import java.util.ArrayList;

public interface IDetailView {
    void showProgressDialog();
    void hideProgressDialog();
    void getDetailNewsList(ArrayList<ArticlesModel> articlesModels);
}
