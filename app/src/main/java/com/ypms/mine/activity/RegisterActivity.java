package com.ypms.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.ToolBarActivity;
import com.ypms.mine.fragment.RegisterFirstFragment;
import com.ypms.mine.fragment.RegisterSecondFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/19.
 */

public class RegisterActivity extends ToolBarActivity {
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    private RegisterFirstFragment registerFirstFragment;
    private RegisterSecondFragment registerSecondFragment;
    private BaseFragment[] fragments;
    public static final int FRIST_FRAGMENT = 0;
    public static final int SECOND_FRAGMENT = 1;
    @Override
    protected String setTittle() {
        return "注册";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register_layout;
    }

    public static void startActivity(Context mContext){
        Intent intent = new Intent(mContext,RegisterActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        registerFirstFragment = new RegisterFirstFragment();
        registerSecondFragment = new RegisterSecondFragment();
        fragments = new BaseFragment[]{registerFirstFragment,registerSecondFragment};
        showFragment(FRIST_FRAGMENT);
    }


    public void showFragment(int val){
        if(fragments[val] == null || isFinishing())
            return;
        for(int i=0; i < fragments.length; i++){
            if(fragments[i] != null && fragments[i].isAdded()){
                    getSupportFragmentManager().beginTransaction().hide(fragments[i]).commitAllowingStateLoss();
            }
        }
        if( fragments[val].isAdded()){
              getSupportFragmentManager().beginTransaction().show(fragments[val]).commitAllowingStateLoss();

        }else {
           getSupportFragmentManager().beginTransaction().add(R.id.ll_content, fragments[val]).commitAllowingStateLoss();

        }
    }
}
