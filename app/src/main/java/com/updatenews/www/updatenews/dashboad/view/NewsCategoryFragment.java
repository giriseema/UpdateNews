package com.updatenews.www.updatenews.dashboad.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.updatenews.www.updatenews.DtosBeans.SourcesModel;
import com.updatenews.www.updatenews.R;
import com.updatenews.www.updatenews.topstories.view.DetailViewActivity;
import com.updatenews.www.updatenews.utils.ConstantClass;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NewsCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private MainActivity parent;
    private RecyclerView recyclerViewNews;
    private GridLayoutManager gridLayoutManager;
    private View view;
    private ArrayList<SourcesModel> sourcesModelsList = new ArrayList<>();
    private FloatingActionButton fab;
    private OnFragmentInteractionListener mListener;

    public NewsCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sourcesModelsList = getArguments().getParcelableArrayList(ConstantClass.SOURCE_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_category, container, false);
        recyclerViewNews = view.findViewById(R.id.recycleView_MainSource);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewNews.setLayoutManager(gridLayoutManager);
        parent = (MainActivity) this.getActivity();
        AppCompatActivity  me = (AppCompatActivity) getActivity();
        fab=((MainActivity)(AppCompatActivity)getActivity()).fab;
        if (sourcesModelsList != null&&sourcesModelsList.size()>0) {
            view.findViewById(R.id.txt_no_data).setVisibility(View.GONE);
            setAdapter(sourcesModelsList);
        }
        else
            view.findViewById(R.id.txt_no_data).setVisibility(View.VISIBLE);

        recyclerViewNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fab.show();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        return view;

    }

    void setAdapter(ArrayList<SourcesModel> sourcesModelsList) {
        if (sourcesModelsList.size() > 0) {
            SourseNewsAdapter sourseNewsAdapter = new SourseNewsAdapter(parent, sourcesModelsList);
            recyclerViewNews.setAdapter(sourseNewsAdapter);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class SourseNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final ArrayList<SourcesModel> sourcesModelArrayList;
        private Context context;

        SourseNewsAdapter(Context context, ArrayList<SourcesModel> VehList) {
            this.context = context;
            this.sourcesModelArrayList = VehList;
        }

        private class ContainerViewHolder extends RecyclerView.ViewHolder {
            TextView titleName;
            TextView typeName;
            RelativeLayout gridView;

            ContainerViewHolder(View view) {
                super(view);
                titleName = (TextView) view.findViewById(R.id.tvNewsNameTitle);
                typeName = (TextView) view.findViewById(R.id.tvNewsCategory);
                gridView = (RelativeLayout) view.findViewById(R.id.gridNews);
            }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.row_grid_list, viewGroup, false);
            return new ContainerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
            final ContainerViewHolder mHolder = (ContainerViewHolder) viewHolder;
            final SourcesModel sourcesModel = sourcesModelArrayList.get(position);

            Spannable span = new SpannableString(sourcesModel.getName());
            span.setSpan(new RelativeSizeSpan(1.5f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new ForegroundColorSpan(0xFFF4511E), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mHolder.titleName.setText(span);
            mHolder.typeName.setText(sourcesModel.getCategory());
            mHolder.gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(parent, DetailViewActivity.class)
                            .putExtra("ChannelListKey", sourcesModelArrayList)
                            .putExtra("KeyName", sourcesModel.getId())
                            .putExtra("KeyLoadingName", sourcesModel.getName()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return sourcesModelArrayList.size();
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
