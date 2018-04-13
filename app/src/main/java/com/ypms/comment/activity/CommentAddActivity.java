package com.ypms.comment.activity;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentAddActivity extends ToolBarActivity {
    @Override
    protected String setTittle() {
        return "写点评";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_comment;
    }
}
