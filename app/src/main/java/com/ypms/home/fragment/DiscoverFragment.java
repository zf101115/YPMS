package com.ypms.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.LazyBaseFragment;
import com.ypms.customWidget.CustomViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/3/2.
 */

public class DiscoverFragment extends LazyBaseFragment {

    private View rootView;
    private List<Fragment> mFragments = new ArrayList<>();

    @BindView(R.id.tv_tab_attention)
    TextView tvTabAttention;
    @BindView(R.id.view_tab_attention)
    View viewTabAttention;
    @BindView(R.id.ll_tab_attention)
    LinearLayout llTabAttention;
    @BindView(R.id.tv_tab_piazza)
    TextView tvTabPiazza;
    @BindView(R.id.view_tab_piazza)
    View viewTabPiazza;
    @BindView(R.id.ll_tab_piazza)
    LinearLayout llTabPiazza;
    @BindView(R.id.view_page)
    CustomViewPage viewPage;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover_layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initViewPage();
        return rootView;
    }

    private void initViewPage() {
        viewPage.setIsScrollAble(false);
        AttentionFragment attentionFragment = new AttentionFragment();
        PiazzaFragment piazzaFragment = new PiazzaFragment();
        mFragments.add(attentionFragment);
        mFragments.add(piazzaFragment);
        viewPage.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments));
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentsList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragmentsList = fragments;
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentsList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    @OnClick(R.id.ll_tab_attention)
    public void tabAttentionClick(){
        viewPage.setCurrentItem(0);
        setTabStatus(0);
    }

    @OnClick(R.id.ll_tab_piazza)
    public void tabPiazzaClick(){
        viewPage.setCurrentItem(1);
        setTabStatus(1);
    }

    private void setTabStatus(int index){
        if (index ==0){
            tvTabAttention.setTextColor(getResources().getColor(R.color.colorPrimary));
            tvTabPiazza.setTextColor(getResources().getColor(R.color.title_dark));
            viewTabAttention.setVisibility(View.VISIBLE);
            viewTabPiazza.setVisibility(View.INVISIBLE);
        }else {
            tvTabAttention.setTextColor(getResources().getColor(R.color.title_dark));
            tvTabPiazza.setTextColor(getResources().getColor(R.color.colorPrimary));
            viewTabAttention.setVisibility(View.INVISIBLE);
            viewTabPiazza.setVisibility(View.VISIBLE);
        }

    }
}
