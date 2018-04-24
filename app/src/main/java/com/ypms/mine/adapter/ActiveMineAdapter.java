package com.ypms.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.CircleImageView;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/24.
 */

public class ActiveMineAdapter extends BaseRecyclerAdapter {


    public ActiveMineAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_active_mine_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        Picasso.with(mContext).load(mechanism.getTitle()).into(viewHolder.ivPic);
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_pic)
        CircleImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_mark)
        TextView tvMark;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_action)
        TextView tvAction;
        @BindView(R.id.iv_cancel)
        ImageView ivCancel;

        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
