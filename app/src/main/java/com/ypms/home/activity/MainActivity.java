package com.ypms.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.ContextUtils;
import com.ypms.common.PageAdapter;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.CustomViewPage;
import com.ypms.home.fragment.DiscoverFragment;
import com.ypms.home.fragment.HomeFragment;
import com.ypms.home.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_discover)
    ImageView ivDiscover;
    @BindView(R.id.tv_discover)
    TextView tvDiscover;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected String setTittle() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initWidget();
    }

    private void initWidget() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), getFragments());
        viewPage.setAdapter(pageAdapter);
        viewPage.setOffscreenPageLimit(2);
        viewPage.setIsScrollAble(false);
        resetTabBg(tvHome,ivHome);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPage.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getFragments() {
        HomeFragment homeFragment = new HomeFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        MeFragment meFragment = new MeFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(discoverFragment);
        fragments.add(meFragment);
//        Fragment[] fragments = new Fragment[]{homeFragment, discoverFragment, meFragment};
        return fragments;
    }


    @OnClick(R.id.ll_home)
    public void homeClick() {
        resetTabBg(tvHome,ivHome);
        viewPage.setCurrentItem(0, false);
    }

    @OnClick(R.id.ll_discover)
    public void discoverClick() {
        resetTabBg(tvDiscover,ivDiscover);
        viewPage.setCurrentItem(1, false);

    }

    @OnClick(R.id.ll_mine)
    public void mineClick() {
        resetTabBg(tvMine,ivMine);
        viewPage.setCurrentItem(2, false);
    }

    private void resetTabBg(TextView tv, ImageView iv) {
        tvHome.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        tvDiscover.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        tvMine.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        ivDiscover.setSelected(false);
        ivHome.setSelected(false);
        ivMine.setSelected(false);
        tv.setTextColor(ContextUtils.getColor(mContext, R.color.colorPrimary));
        iv.setSelected(true);
    }

}
