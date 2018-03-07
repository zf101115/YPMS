package com.ypms.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.R;
import com.ypms.common.recycleView.BaseRecyclerAdapter;
import com.ypms.common.recycleView.BaseRecyclerViewHolder;
import com.ypms.common.recycleView.RecyclerItemClickListener;

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
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_header, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }


    class ViewHolder extends BaseRecyclerViewHolder{



        public ViewHolder(View itemView) {
            super(itemView, mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
