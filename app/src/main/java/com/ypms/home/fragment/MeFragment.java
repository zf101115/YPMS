package com.ypms.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.LazyBaseFragment;
import com.ypms.mine.activity.ActiveMineActivity;
import com.ypms.mine.activity.LoginActivity;
import com.ypms.mine.activity.TeacherCollectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/3/2.
 */

public class MeFragment extends LazyBaseFragment {

    @BindView(R.id.ll_mine_active)
    LinearLayout llMineActive;

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
    @OnClick(R.id.ll_mine_active)
    public void mineActiveClick(){
        ActiveMineActivity.startActivity(mContext);
    }
    @OnClick(R.id.ll_course)
    public void mineCourseClick(){
        ActiveMineActivity.startActivity(mContext);
    }
    @OnClick(R.id.ll_teacher)
    public void teacherClick(){
        TeacherCollectActivity.startActivity(mContext);
    }

    @Override
    protected void lazyLoad() {

    }
}
