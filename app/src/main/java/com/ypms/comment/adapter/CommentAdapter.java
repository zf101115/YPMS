package com.ypms.comment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.CircleImageView;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentAdapter extends BaseRecyclerAdapter {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_comment_layout,parent,false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        Picasso.with(mContext).load(mechanism.getTitle()).into(viewHolder.avatar);
    }

    class ViewHolder extends BaseRecyclerViewHolder{
        @BindView(R.id.avatar)
        CircleImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
