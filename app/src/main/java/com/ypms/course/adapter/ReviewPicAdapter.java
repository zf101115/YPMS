package com.ypms.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.BaseListAdapter;
import com.ypms.home.model.Mechanism;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/12.
 */

public class ReviewPicAdapter extends BaseListAdapter {

    public ReviewPicAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(null==convertView){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_review_pic, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String mechanism = (String) items.get(position);
        if (mechanism!=null&&!"".equals(mechanism)){
            Picasso.with(mContext).load(mechanism).into(holder.ivPic);
        }
        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
