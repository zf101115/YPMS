package com.ypms.area.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.area.model.Area;
import com.ypms.common.BaseListAdapter;
import com.ypms.common.ContextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/13.
 */

public class PopAreaAdapter extends BaseListAdapter {

    public PopAreaAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_area_screen,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Area area = (Area) items.get(i);
        holder.tv.setText(area.getName());
        holder.tv.setTextColor(area.isSelect()? ContextUtils.getColor(mContext,R.color.colorPrimary):ContextUtils.getColor(mContext,R.color.text_search_grey));
        holder.iv.setVisibility(area.isSelect()?View.VISIBLE:View.GONE);
        return convertView;
    }


    class ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        public ViewHolder(View v){
            ButterKnife.bind(this,v);
        }
    }
}
