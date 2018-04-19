package com.ypms.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.LazyBaseFragment;
import com.ypms.mine.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/3/2.
 */

public class MeFragment extends LazyBaseFragment {

    private View containerView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView =  super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,containerView);
        return containerView;
    }

    @OnClick(R.id.tv_login)
    public void loginClick(){
        LoginActivity.startActivity(mContext);
    }

    @Override
    protected void lazyLoad() {

    }
}
