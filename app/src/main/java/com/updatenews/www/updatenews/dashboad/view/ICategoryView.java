package com.updatenews.www.updatenews.dashboad.view;

import com.updatenews.www.updatenews.DtosBeans.SourcesModel;

import java.util.ArrayList;

public interface ICategoryView {
    void showProgressDialog();
    void hideProgressDialog();
    void getChannelList(ArrayList<SourcesModel> sourcesModelsList,boolean isResult);
}
