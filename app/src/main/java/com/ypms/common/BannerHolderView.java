package com.ypms.common;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;

/**
 * Created by Hero on 2018/4/3.
 */

public class BannerHolderView implements Holder<String> {
    private bannerClickLinster linster;
    private String data;
    private Integer position;
    private ImageView imageView;

    public BannerHolderView(bannerClickLinster listener){
        this.linster = listener;
    }

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linster.OnItemClick(data,position);
            }
        });
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        this.position = position;
        this.data = data;
        Picasso.with(context).load(data).into(imageView);
    }
}
