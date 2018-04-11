package com.ypms.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.RoundRectImageView;
import com.ypms.home.adapter.HomeAdapter;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/11.
 */

public class BulkAdapter extends BaseRecyclerAdapter {

    public BulkAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_bulk_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        viewHolder.tvTitle.setText(mechanism.getTitle());
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_bulk)
        RoundRectImageView ivBulk;
        @BindView(R.id.tv_class_num)
        TextView tvClassNum;
        @BindView(R.id.tv_class_pay)
        TextView tvClassPay;
        @BindView(R.id.ll_bulk_pay)
        LinearLayout llBulkPay;
        @BindView(R.id.ll_bulk_info)
        LinearLayout llBulkInfo;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
