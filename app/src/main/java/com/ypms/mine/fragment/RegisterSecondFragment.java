package com.ypms.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/19.
 */

public class RegisterSecondFragment extends BaseFragment {
    @BindView(R.id.tv_step)
    TextView tvStep;
    @BindView(R.id.next)
    TextView next;
    private View containerView;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register_first;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView =  super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,containerView);
        tvStep.setText("步骤二");
        next.setText("完成");
        return containerView;
    }
}
