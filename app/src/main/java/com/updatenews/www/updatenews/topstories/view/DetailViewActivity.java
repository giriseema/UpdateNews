package com.updatenews.www.updatenews.topstories.view;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;
import com.updatenews.www.updatenews.R;
import com.updatenews.www.updatenews.topstories.presenter.ViewDetailPresenter;
import com.updatenews.www.updatenews.utils.CommonProgressDialog;
import com.updatenews.www.updatenews.utils.ConstantClass;

import java.util.ArrayList;

public class DetailViewActivity extends AppCompatActivity implements IDetailView{

    private ViewDetailPresenter presenter;
    private RecyclerView recyclerViewChannelNews;
    private LinearLayoutManager linearLayoutManager;
    private SourseNewsAdapter sourseNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_view);
        String title=getIntent().getStringExtra("KeyLoadingName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter=new ViewDetailPresenter(this);
        recyclerViewChannelNews = (RecyclerView) findViewById(R.id.rcView_channelNews);
        if (ConstantClass.isNetworkAvailable(this)) {
            showProgressDialog();
            presenter.getDetailNewsList(getIntent().getStringExtra("KeyName"));
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showProgressDialog() {
        CommonProgressDialog.showLoader(this);
    }

    @Override
    public void hideProgressDialog() {
        CommonProgressDialog.hideLoader();
    }

    @Override
    public void getDetailNewsList(ArrayList<ArticlesModel> articlesModels) {
        hideProgressDialog();
   if (articlesModels.size() > 0) {
                                    linearLayoutManager = new LinearLayoutManager(this);
                                    linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
                                    recyclerViewChannelNews.setLayoutManager(linearLayoutManager);
                         sourseNewsAdapter = new SourseNewsAdapter(this, articlesModels, false);
                                    recyclerViewChannelNews.setAdapter(sourseNewsAdapter);
                                }
    }

    class SourseNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final ArrayList<ArticlesModel> newsArrayList;
        private Context context;
        SourseNewsAdapter(Context context, ArrayList<ArticlesModel> VehList, boolean isNames) {
            this.context = context;
            this.newsArrayList = VehList;
        }

        private class ContainerViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitleNameList;
            ImageView imageViewNews;
            LinearLayout newsLayout;


            ContainerViewHolder(View view) {
                super(view);
                newsLayout = (LinearLayout) view.findViewById(R.id.rlNewsLayout);
                newsTitleNameList = (TextView) view.findViewById(R.id.tvNewsTitle);
                imageViewNews = (ImageView) view.findViewById(R.id.imgNewsLogo);
            }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.row_news_list, viewGroup, false);
            return new ContainerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
            final ContainerViewHolder mHolder = (ContainerViewHolder) viewHolder;
            final ArticlesModel articlesModel = newsArrayList.get(position);
            Glide.with(DetailViewActivity.this).load(articlesModel.getUrlToImage()).apply(new RequestOptions()
                    .placeholder(R.drawable.placeholder)).into(mHolder.imageViewNews);
            mHolder.newsTitleNameList.setText(articlesModel.getTitle());

            mHolder.newsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* startActivity(new Intent(context, DisplayArticalActivity.class)
                            .putExtra("ArticlesModelListKey", newsArrayList)
                            .putExtra("ArticlesModelPositionKey", position + ""));*/

                    DetailFragment detailFragment = DetailFragment.newInstance(articlesModel);
                    detailFragment.show(getSupportFragmentManager(),"TAG");
                }
            });
        }

        @Override
        public int getItemCount() {
            int countSize = newsArrayList.size();
            return countSize;
        }

    }

}
