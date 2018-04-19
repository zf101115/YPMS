package com.ypms.comment.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ypms.R;
import com.ypms.comment.adapter.CommentPicAdapter;
import com.ypms.comment.bean.CommentPic;
import com.ypms.common.ContextUtils;
import com.ypms.common.PermissionCall;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.NoScrollGridView;
import com.ypms.photopick.imageloader.PhotoPickActivity;

import java.io.File;
import java.util.ArrayList;
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
    private static final int CAMERA_REQUEST_CODE = 1;
    private CommentPicAdapter commentPicAdapter;
    private int leftNum;
    public static final int LIMIT_PIC_NUM = 9;
    private String fileName = "";
    private File thumbFile;

    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.grid_pic)
    NoScrollGridView gridPic;


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
        initWidget();
    }

    private void initWidget() {
        commentPicAdapter = new CommentPicAdapter(mContext,LIMIT_PIC_NUM);
        commentPicAdapter.setItems(new ArrayList());
        gridPic.setAdapter(commentPicAdapter);
    }

    public void addPic(){
        leftNum = LIMIT_PIC_NUM - commentPicAdapter.getCount() + 1;
        showPhotoPop();

    }
    @OnClick(R.id.tv_commit)
    public void commitClick(){

    }

    private void checkPhotoPermission(){
        getPermission(new PermissionCall() {
            @Override
            public void success(List<Integer> list) {
                //TODO:（全部）申请成功的处理
                Intent intent = new Intent(CommentAddActivity.this, PhotoPickActivity.class);
                intent.putExtra(PhotoPickActivity.EXTRA_PICNUM, leftNum);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
            @Override
            public void fail(List<Integer> list) {
                Toast.makeText(mContext,list.size()+"失败数目",Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
    private void checkTakePhotoPermission(){
        getPermission(new PermissionCall() {
            @Override
            public void success(List<Integer> list) {
                //TODO:（全部）申请成功的处理
                if (list.get(0)==-1||list.size()==2||list.get(0)==0){
                    //TODO:（全部）申请成功的处理
                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 判断存储卡是否可以用，可用进行存储
                    if (ContextUtils.hasSdcard()) {
                        fileName = "temp" + System.currentTimeMillis() + ".jpg";
                        thumbFile = new File(Environment.getExternalStorageDirectory(), fileName);
                        thumbFile.getParentFile().mkdirs();
                        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(thumbFile));
                    }
                    startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                }
            }
            @Override
            public void fail(List<Integer> list) {
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == IMAGE_REQUEST_CODE&&data!=null){
            List<String> imgPathList = (List<String>) data.getSerializableExtra(PhotoPickActivity.RESULT_PICNUM);
            List<CommentPic> list = new ArrayList<>();
            if (!ContextUtils.isEmpty(imgPathList)) {
                for (String path : imgPathList) {
                    CommentPic item = new CommentPic(path);
                    list.add(item);
                }
                commentPicAdapter.appendItems(list);
            }
        }else if (requestCode == CAMERA_REQUEST_CODE && thumbFile != null) {
                commentPicAdapter.appendItem(new CommentPic(thumbFile.getPath()));
            }
    }

    private void showPhotoPop(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_select_photo, null);
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
        window.setAnimationStyle(R.style.dialog_anim_bottom);
        window.showAtLocation(tvCommit, Gravity.BOTTOM, 0, 0);
        TextView takePic = (TextView) view.findViewById(R.id.take_pic);
        TextView choosePic = (TextView) view.findViewById(R.id.choose_pic);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                checkTakePhotoPermission();
            }
        });
        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                checkPhotoPermission();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp =getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
