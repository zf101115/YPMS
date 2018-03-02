package com.ypms.home.activity;

import android.os.Bundle;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

public class MainActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}
