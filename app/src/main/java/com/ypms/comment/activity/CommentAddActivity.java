package com.ypms.comment.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ypms.R;
import com.ypms.common.PermissionCall;
import com.ypms.common.ToolBarActivity;
import com.ypms.photopick.imageloader.PhotoPickActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentAddActivity extends ToolBarActivity {

    /* 请求码*/
    private static final int IMAGE_REQUEST_CODE = 0;


    @BindView(R.id.tv_commit)
    TextView tvCommit;


    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, CommentAddActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected String setTittle() {
        return "写点评";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_commit)
    public void commitClick(){
      getPermission(new PermissionCall() {
                    @Override
                    public void success(List<Integer> list) {

                        //TODO:（全部）申请成功的处理
                        Intent intent = new Intent(CommentAddActivity.this, PhotoPickActivity.class);
                        intent.putExtra(PhotoPickActivity.EXTRA_PICNUM, 9);
                        startActivityForResult(intent, IMAGE_REQUEST_CODE);
                    }
                    @Override
                    public void fail(List<Integer> list) {
                        Toast.makeText(mContext,list.size()+"失败数目",Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
