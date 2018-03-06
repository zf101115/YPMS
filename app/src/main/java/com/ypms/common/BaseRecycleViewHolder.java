package com.ypms.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hero on 2018/3/5.
 */

public class BaseRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    private RecyclerItemClickListener mClickListener;

    public BaseRecycleViewHolder(View itemView, final RecyclerItemClickListener mClickListener) {
        super(itemView);
        this.mClickListener = mClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mClickListener != null){
            if (getLayoutPosition()<=0)return;
            mClickListener.OnItemClick(v,getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mClickListener != null){
            if (getLayoutPosition()<=0) return false;
            mClickListener.OnItemLongClick(view,getLayoutPosition());
        }
        return true;
    }
}
