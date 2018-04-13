package com.ypms.comment.activity;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentAllActivity extends ToolBarActivity {
    @Override
    protected String setTittle() {
        return "全部点评";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_single_rv;
    }
}
