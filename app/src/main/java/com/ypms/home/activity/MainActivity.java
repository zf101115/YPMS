package com.ypms.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.LinearLayout;

import com.ypms.R;
import com.ypms.common.PageAdapter;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.CustomViewPage;
import com.ypms.home.fragment.DiscoverFragment;
import com.ypms.home.fragment.HomeFragment;
import com.ypms.home.fragment.MeFragment;

import butterknife.BindView;

public class MainActivity extends ToolBarActivity {


    @BindView(R.id.view_page)
    CustomViewPage viewPage;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_discover)
    LinearLayout llDiscover;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.bottom)
    LinearLayout bottom;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    private void initWidget() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),getFragments());
        viewPage.setAdapter(pageAdapter);
        viewPage.setOffscreenPageLimit(3);
    }

    private Fragment[] getFragments() {
        HomeFragment homeFragment = new HomeFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        MeFragment meFragment = new MeFragment();
        Fragment[] fragments = new Fragment[]{homeFragment, discoverFragment, meFragment};
        return fragments;
    }

}
