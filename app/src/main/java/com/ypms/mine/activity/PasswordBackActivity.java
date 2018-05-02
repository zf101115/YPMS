package com.ypms.mine.activity;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

/**
 * Created by Hero on 2018/5/2.
 */

public class PasswordBackActivity extends ToolBarActivity {
    @Override
    protected String setTittle() {
        return "找回密码";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_password_back_layout;
    }
}
