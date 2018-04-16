package com.ypms.comment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.course.adapter.ReviewPicAdapter;
import com.ypms.customWidget.NoScrollGridView;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/16.
 */

public class CommentAllAdapter extends BaseRecyclerAdapter {
    private List<String> pics = new ArrayList<>();
    public CommentAllAdapter(Context context) {
        super(context);
    }

    public void setPics(List<String> pics){
        this.pics = pics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_commemt_all_layout,parent,false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        viewHolder.tvNick.setText(mechanism.getTitle());
        ReviewPicAdapter reviewPicAdapter = new ReviewPicAdapter(mContext);
        reviewPicAdapter.setItems(pics);
        viewHolder.gridView.setAdapter(reviewPicAdapter);
    }

    class ViewHolder extends BaseRecyclerViewHolder{

        @BindView(R.id.tv_nick)
        TextView tvNick;
        @BindView(R.id.grid_pic)
        NoScrollGridView gridView;

     public ViewHolder(View itemView){
         super(itemView, mItemClickListener);
         ButterKnife.bind(this, itemView);
     }
 }

}
