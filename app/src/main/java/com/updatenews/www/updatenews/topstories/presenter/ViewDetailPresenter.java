package com.updatenews.www.updatenews.topstories.presenter;

import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;
import com.updatenews.www.updatenews.topstories.model.IViewDetailModel;
import com.updatenews.www.updatenews.topstories.model.ViewDetailModel;
import com.updatenews.www.updatenews.topstories.view.DetailViewActivity;
import com.updatenews.www.updatenews.topstories.view.IDetailView;

import java.util.ArrayList;

public class ViewDetailPresenter implements IViewDetailPresenter,IViewDetailModel.WSResponse {
    private IViewDetailModel iViewDetailModel;
    private IDetailView detailView;

    public ViewDetailPresenter(DetailViewActivity context) {
        iViewDetailModel=new ViewDetailModel();
        detailView=context;
    }

    @Override
    public void getDetailNewsList(String channelKey) {
        if(iViewDetailModel!=null)
            iViewDetailModel.getDetailListNews(channelKey,this);
    }

    @Override
    public void response(ArrayList<ArticlesModel> articlesModels, boolean isSuccess) {
      if(articlesModels!=null&&isSuccess) {
          detailView.getDetailNewsList(articlesModels);
      }
    }
}
