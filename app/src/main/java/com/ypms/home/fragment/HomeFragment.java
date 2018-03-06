package com.ypms.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.LazyBaseFragment;
import com.ypms.home.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/2.
 */

public class HomeFragment extends LazyBaseFragment {

    @BindView(R.id.rv)
    XRecyclerView rv;

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
        ButterKnife.bind(this, rootView);
        initView(inflater);
        isCreatView = true;
        return rootView;
    }

    private void initView(LayoutInflater inflater) {
        View header = inflater.inflate(R.layout.layout_home_header,null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.addHeaderView(header);


    }

    @Override
    protected void lazyLoad() {
        if (null != mActivity && isCreatView && isVisible) {
            initDate();
        }
    }

    private void initDate() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
