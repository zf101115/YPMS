package com.ypms.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.common.BaseRecycleViewHolder;
import com.ypms.common.RecyclerItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/5.
 */

public class HomeAdapter extends RecyclerView.Adapter<BaseRecycleViewHolder>{
    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeViewHolder extends BaseRecycleViewHolder{


        public HomeViewHolder(View itemView, RecyclerItemClickListener mClickListener) {
            super(itemView, mClickListener);
            ButterKnife.bind(this,itemView);
        }
    }
}
