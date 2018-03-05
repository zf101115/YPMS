package com.ypms.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.LazyBaseFragment;
import com.ypms.home.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/2.
 */

public class HomeFragment extends LazyBaseFragment {

    private View rootView;
    private boolean isCreatView;
    private MainActivity mActivity;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,rootView);
        isCreatView = true;
        return rootView;
    }

    @Override
    protected void lazyLoad() {
        if (null!=mActivity&&isCreatView&&isVisible){
            initView();
        }
    }

    private void initView() {

    }
}
