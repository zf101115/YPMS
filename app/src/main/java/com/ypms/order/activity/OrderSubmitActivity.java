package com.ypms.order.activity;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

/**
 * Created by Hero on 2018/4/10.
 */

public class OrderSubmitActivity extends ToolBarActivity {
    @Override
    protected String setTittle() {
        return "提交订单";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_submit;
    }
}
