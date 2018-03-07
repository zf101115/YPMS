package com.ypms.common.recycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hero on 2018/3/6.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected List<T> items;

    protected LayoutInflater mInflater;

    protected Context mContext;

    protected RecyclerItemClickListener mItemClickListener;

    public void setOnItemClickListener(RecyclerItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.items = new ArrayList<T>();
    }


    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(int position,T item){
        this.items.add(position,item);
        notifyItemInserted(position);
    }

    public void appendItem(T item){
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void appendItems(List<T> appendItems){
        if(appendItems.size()>0) {
            this.items.addAll(appendItems);
            notifyDataSetChanged();
        }
    }

    public void preAppendItems(List<T> preAppendItems){
        if(preAppendItems.size()>0){
            for(int i=preAppendItems.size()-1;i>=0;i--){
                this.items.add(0,preAppendItems.get(i));
            }
            notifyDataSetChanged();
        }
    }

    public void removeItemAtPosition(int position){
        this.items.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllItems(){
        this.items.clear();
        notifyDataSetChanged();
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
