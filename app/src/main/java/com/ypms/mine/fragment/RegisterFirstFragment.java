package com.ypms.mine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.SpUtils;
import com.ypms.mine.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/19.
 */

public class RegisterFirstFragment extends BaseFragment {
    private View containerView;
    private SpUtils spUtils;
    private int time = 60;

    /**
     * Handler参数
     */
    private final int MSG_TIME = 30;
    private final int MSG_RESET = 31;
    private final int MSG_START = 32;
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
        spUtils = new SpUtils(getActivity());
        long lastTime = spUtils.getLong(SpUtils.SP_VERTIFY_TIME,0);
        if((System.currentTimeMillis()-lastTime)/1000<50){
            time = (int) (60-(System.currentTimeMillis()-lastTime)/1000);
            Log.e("==time",time+"");
            mHandler.sendEmptyMessage(MSG_START);
        }
        tvSms.setVisibility(View.VISIBLE);
        return containerView;
    }

    @OnClick(R.id.next)
    public void nextClick(){

        act.showFragment(RegisterActivity.SECOND_FRAGMENT);
    }

    @OnClick(R.id.tv_sms)
    public void smsClick(){
        spUtils.setLong(SpUtils.SP_VERTIFY_TIME,System.currentTimeMillis());
        mHandler.sendEmptyMessage(MSG_START);
    }



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvSms.setText(getTime());
            switch (msg.what){
                case MSG_START:
                    tvSms.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_grey_grey_radius));
                    sendEmptyMessage(MSG_TIME);
                    break;
                case MSG_TIME:
                    time--;
                    if(hasMessages(MSG_TIME)){
                        removeMessages(MSG_TIME);
                    }
                    if(time>0){
                        sendEmptyMessageDelayed(MSG_TIME,1000);
                    }else {
                        sendEmptyMessageDelayed(MSG_RESET,1000);
                    }
                    break;
                case MSG_RESET:
                    time = 60;
                    removeCallbacksAndMessages(null);
                    tvSms.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_gery_white_5_round));
            }
        }
    };

    private String getTime(){
        if(time == 0){
            return "发送验证码";
        }
        Log.e("==time",time+"");
        String strTime = time<10?"0"+time:""+time;
        return "("+strTime+"秒)后重发";
    }
}
