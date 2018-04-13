package com.ypms.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.NoScrollGridView;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/12.
 */

public class ReviewInfoAdapter extends BaseRecyclerAdapter {
    private List<String> pics = new ArrayList<>();

    public ReviewInfoAdapter(Context context) {
        super(context);
    }
    public void setPics(List<String> pics){
        this.pics = pics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        ReviewPicAdapter reviewPicAdapter = new ReviewPicAdapter(mContext);
        reviewPicAdapter.setItems(pics);
        ((ViewHolder) holder).gridView.setAdapter(reviewPicAdapter);
    }

    class ViewHolder extends BaseRecyclerViewHolder{

        @BindView(R.id.grid_pic)
        NoScrollGridView gridView;

        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
