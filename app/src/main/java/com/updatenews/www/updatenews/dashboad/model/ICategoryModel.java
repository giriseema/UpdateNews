package com.updatenews.www.updatenews.dashboad.model;

import com.updatenews.www.updatenews.DtosBeans.SourcesModel;

import java.util.ArrayList;

public interface ICategoryModel {
    void getListOfChannels(WSResponse wsResponse);

    interface WSResponse {
        void response(ArrayList<SourcesModel> res, boolean isError);
    }
}
