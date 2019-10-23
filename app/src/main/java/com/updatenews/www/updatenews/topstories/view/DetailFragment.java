package com.updatenews.www.updatenews.topstories.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.updatenews.www.updatenews.DtosBeans.ArticlesModel;
import com.updatenews.www.updatenews.R;
import com.updatenews.www.updatenews.utils.ConstantClass;

public class DetailFragment extends DialogFragment {
    private View view;
    private ArticlesModel articlesModel;
    private TextView description;
    private TextView title;
    private TextView close;
    private TextView shareNewsBtn;
    private ImageView logoImage;
    public static DetailFragment newInstance(ArticlesModel articlesModel) {
        DetailFragment dialogFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ARTICLE_KEY", articlesModel);
        dialogFragment.setArguments(bundle);

        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articlesModel = new ArticlesModel();
            articlesModel = (ArticlesModel) getArguments().getParcelable("ARTICLE_KEY");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.detail_dialog_fragment, container, false);
        description=view.findViewById(R.id.description);
        title=view.findViewById(R.id.title);
        logoImage=view.findViewById(R.id.newsLogoImg);
        close=view.findViewById(R.id.close_dialog);
        shareNewsBtn=view.findViewById(R.id.share_news_btn);
        Glide.with(getActivity()).load(articlesModel.getUrlToImage()).apply(new RequestOptions()
                .placeholder(R.drawable.placeholder)).into(logoImage);
        title.setText(articlesModel.getTitle());
        Spannable span = new SpannableString(articlesModel.getDescription());
        span.setSpan(new RelativeSizeSpan(1.5f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(0xFF00675b), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        description.setText(span);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        shareNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,articlesModel.getUrl());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        return view;
    }
}
