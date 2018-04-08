package com.ypms.institution.adapter;

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
 * Created by Hero on 2018/4/8.
 */

public class TeacherAdapter extends BaseRecyclerAdapter{
    public TeacherAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_teacher, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final Mechanism mechanism = (Mechanism) items.get(position);
            Picasso.with(mContext).load(mechanism.getTitle()).into(viewHolder.avatar);
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.avatar)
        CircleImageView avatar;
        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
