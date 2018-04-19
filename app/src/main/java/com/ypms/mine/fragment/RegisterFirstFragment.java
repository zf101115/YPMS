package com.ypms.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.mine.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/19.
 */

public class RegisterFirstFragment extends BaseFragment {
    private View containerView;
    @BindView(R.id.tv_sms)
    TextView tvSms;

    private RegisterActivity act;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register_first;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView =  super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,containerView);
        if (getActivity() != null) {
            act = (RegisterActivity) getActivity();
        }
        tvSms.setVisibility(View.VISIBLE);
        return containerView;
    }

    @OnClick(R.id.next)
    public void nextClick(){
        act.showFragment(RegisterActivity.SECOND_FRAGMENT);
    }
}
