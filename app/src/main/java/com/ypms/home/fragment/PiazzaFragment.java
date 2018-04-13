package com.ypms.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.LazyBaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/12.
 */

public class PiazzaFragment extends BaseFragment {

    private View containerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView =  super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(containerView);

        return containerView;

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_piazza_layout;
    }

}
