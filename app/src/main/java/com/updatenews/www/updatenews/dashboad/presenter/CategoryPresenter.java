package com.updatenews.www.updatenews.dashboad.presenter;

import com.updatenews.www.updatenews.DtosBeans.SourcesModel;
import com.updatenews.www.updatenews.dashboad.model.CategoryModel;
import com.updatenews.www.updatenews.dashboad.model.ICategoryModel;
import com.updatenews.www.updatenews.dashboad.view.ICategoryView;
import com.updatenews.www.updatenews.dashboad.view.MainActivity;
import com.updatenews.www.updatenews.dashboad.view.NewsCategoryFragment;

import java.util.ArrayList;

public class CategoryPresenter implements ICategoryPresenter, ICategoryModel.WSResponse {
    private ICategoryView iCategoryView;
    private ICategoryModel iCategoryModel;

    public CategoryPresenter(MainActivity context) {
        iCategoryModel = new CategoryModel();
        iCategoryView = context;
    }

    @Override
    public void getListOfNewsChannel() {
        iCategoryModel.getListOfChannels(this);
    }

    @Override
    public void response(ArrayList<SourcesModel> modelArrayList, boolean isResult) {
        if(iCategoryView!=null)
        iCategoryView.getChannelList(modelArrayList,isResult);
    }
}
