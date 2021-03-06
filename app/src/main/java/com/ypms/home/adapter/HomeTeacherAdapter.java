package com.ypms.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.customWidget.CircleImageView;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/27.
 */

public class HomeTeacherAdapter extends BaseRecyclerAdapter {
    public final int VIEW_TYPE_HEAD = 1;
    public final int VIEW_TYPE_LIST = 2;
    public HomeTeacherAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType==VIEW_TYPE_HEAD){
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_teacher_header, parent, false);
            return new HeaderHolder(itemView);
        }else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_teacher, parent, false);
            return new ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (holder.getItemViewType()==VIEW_TYPE_HEAD){
             HeaderHolder headerHolder = (HeaderHolder) holder;
        }else {
            ViewHolder viewHolder = (ViewHolder) holder;
            final Mechanism mechanism = (Mechanism) items.get(position);
            Picasso.with(mContext).load(mechanism.getTitle()).into(viewHolder.avatar);
        }
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.avatar)
        CircleImageView avatar;
        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
    class HeaderHolder extends BaseRecyclerViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView, mItemClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return VIEW_TYPE_HEAD;
        }else {
            return VIEW_TYPE_LIST;
        }
    }
}
