package com.ypms.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.CountView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/10.
 */

public class OrderSubmitActivity extends ToolBarActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.countview)
    CountView countView;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, OrderSubmitActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        countView.setMaxCount(100);
        countView.setOnAmountChangeListener(new CountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                tvPrice.setText("¥"+1000*amount);
            }
        });
    }

    @Override
    protected String setTittle() {
        return "提交订单";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_submit;
    }
}
