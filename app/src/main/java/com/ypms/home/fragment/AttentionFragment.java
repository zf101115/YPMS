package com.ypms.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.PageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/12.
 */

public class AttentionFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    private String[] title = new String[]{"全部","体验课","在线课程","成果秀","超市","英语角","幼儿","广场"};

    private View containerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,containerView);
        initWidget();
        return containerView;
    }

    private void initWidget() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i=0;i<title.length;i++){
            Fragment fragment = new EmbedFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",title[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPage.setAdapter(new TabFragmentAdapter(fragments,title,getChildFragmentManager(),mContext));
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.setTabTextColors(getResources().getColor(R.color.title_dark), getResources().getColor(R.color.colorPrimary));
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_attention_layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
