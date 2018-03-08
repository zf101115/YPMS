package com.ypms.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.customWidget.StartView;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/5.
 */

public class HomeAdapter extends BaseRecyclerAdapter {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Mechanism mechanism = (Mechanism) items.get(position);
        viewHolder.tvTitle.setText(mechanism.getTitle());
<<<<<<< HEAD
//        viewHolder.start.setStarMark(3.6f);
=======
        viewHolder.start.setStarMark(3.6f);
>>>>>>> bed4544d4dc5e21dfda4a04908091f976c6875a0
//        Glide.with(mContext).load(mechanism.getPic()).into(viewHolder.ivPic);
    }


    class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
<<<<<<< HEAD
//        @BindView(R.id.start)
//        StartView start;
=======
        @BindView(R.id.start)
        StartView start;
>>>>>>> bed4544d4dc5e21dfda4a04908091f976c6875a0
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_model)
        TextView tvModel;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.tv_active_top)
        TextView tvActiveTop;
        @BindView(R.id.tv_active_bottom)
        TextView tvActiveBottom;

        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
