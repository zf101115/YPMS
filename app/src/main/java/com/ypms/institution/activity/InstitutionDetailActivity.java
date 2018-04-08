package com.ypms.institution.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/5.
 */

public class InstitutionDetailActivity extends ToolBarActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, InstitutionDetailActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_intitution_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivPic);

    }
}
