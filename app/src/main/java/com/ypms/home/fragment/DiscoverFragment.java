package com.ypms.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.MapView;
import com.ypms.R;
import com.ypms.common.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/2.
 */

public class DiscoverFragment extends BaseFragment {

//    @BindView(R.id.map)
//    MapView map;

    private View rootView;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover_layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,rootView);
        return rootView;
    }
}
