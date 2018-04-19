package com.ypms.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hero on 2018/3/13.
 */

public abstract class BaseListAdapter<T> extends BaseAdapter{

    protected List<T> items;

    protected LayoutInflater mInflater;

    protected Context mContext;


    public BaseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.items = new ArrayList<T>();
    }


    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    public void appendItems(List<T> appendItems){
        if(appendItems.size()>0) {
            this.items.addAll(appendItems);
            notifyDataSetChanged();
        }
    }

    public void appendItem(T item){
        this.items.add(item);
        notifyDataSetChanged();
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
        notifyDataSetChanged();
    }

    public void removeAllItems(){
        this.items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
