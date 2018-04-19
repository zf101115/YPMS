package com.ypms.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/19.
 */

public class LoginActivity extends ToolBarActivity {
    @Override
    protected String setTittle() {
        return "登陆";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login_layout;
    }
    public static void startActivity(Context mContext){
        Intent intent = new Intent(mContext,LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_register)
    public void registerClick(){
        RegisterActivity.startActivity(mContext);
    }
}
