package com.ypms.photopick.imageloader;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.CustomToast;
import com.ypms.photopick.bean.ImageFloder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class PhotoPickActivity extends ToolBarActivity implements ListImageDirPopupWindow.OnImageDirSelected {
    private ProgressDialog mProgressDialog;
    public static final String EXTRA_PICNUM = "com.eyaos.nmp.photopic.PICNUM";
    public static final String RESULT_PICNUM = "com.eyaos.nmp.photopic.RESULT";
    private int resultCode = 8;
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs;
    private GridView mGirdView;
    private MyAdapter mAdapter;
    private int picNum;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    private RelativeLayout mBottomLy;
    private TextView mChooseDir;
    private TextView mImageCount;
    private int mScreenHeight;
    private String bigPicPath;
    private ListImageDirPopupWindow mListImageDirPopupWindow;
    private List<String> mSelectedImage;
    private View viewBottom;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_pick;
    }

    @Override
    protected String setTittle() {
        return "选择图片";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGirdView = (GridView) findViewById(R.id.id_gridView);
        mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
        mImageCount = (TextView) findViewById(R.id.id_total_count);
        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        viewBottom = findViewById(R.id.id_view_bottom);
        mSelectedImage = new LinkedList<String>();
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        getImages();
        initEvent();
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            CustomToast.showToastAtTop(getApplicationContext(),"暂无外部存储",R.drawable.custom_toast_ok,3000);
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = PhotoPickActivity.this.getContentResolver();
                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    if (parentFile.list() == null) continue;
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg") || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg") || filename.endsWith(".JPG")
                                    || filename.endsWith(".PNG") || filename.endsWith(".JPEG"))
                                return true;
                            return false;
                        }
                    }).length;
                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);
                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mProgressDialog.dismiss();
            // 为View绑定数据
            initGridView();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
//            showPopWindow();
        }
    };

    /**
     * 为View绑定数据
     */
    private void initGridView() {
        if (mImgDir == null) {
            CustomToast.showToastAtTop(getApplicationContext(),"没有找到图片",R.drawable.custom_toast_fail,3000);
            //Toast.makeText(getApplicationContext(), "没有找到图片", Toast.LENGTH_SHORT).show();
            return;
        }
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg") || filename.endsWith(".JPG")
                        || filename.endsWith(".PNG") || filename.endsWith(".JPEG"))
                    return true;
                return false;
            }
        }));
//        mChooseDir.setText("" + mImgDir.getPath());
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        Intent intent = getIntent();
        picNum = (int) intent.getSerializableExtra(EXTRA_PICNUM);
        mAdapter = new MyAdapter(getApplicationContext(), mImgs, R.layout.photo_grid_item, mImgDir.getAbsolutePath(), this, picNum);
        Collections.reverse(mImgs);
        mGirdView.setAdapter(mAdapter);
    }

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7), mImageFloders,
                LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_list, null));
        mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);
    }

    private void initEvent() {
        /**
         * 为底部的布局设置点击事件，弹出popupWindow
         */
        mChooseDir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
        mImageCount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mSelectedImage && mSelectedImage.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra(RESULT_PICNUM, (Serializable) mSelectedImage);
                    setResult(resultCode, intent);
                    finish();
                }
            }
        });
    }

    /**
     * 显示pop
     */
    private void showPopWindow() {
        if(null == mImgDir){
            return;
        }
        mListImageDirPopupWindow.setAnimationStyle(R.style.dialog_anim_bottom);
        mListImageDirPopupWindow.showAsDropDown(viewBottom, 0, 0);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);
    }

    public void change(List<String> mSelectedImage) {
        this.mSelectedImage = mSelectedImage;
        mImageCount.setText("完成（" + mSelectedImage.size() + "）");

    }

    public void bigPic(String bigPicPath) {
        // TODO: 2016/4/13
        this.bigPicPath = bigPicPath;
//        LargeImageActivity.activityStart(this, bigPicPath);
    }

    @Override
    public void selected(ImageFloder floder) {
        mImgDir = new File(floder.getDir());
//        mChooseDir.setText("" + mImgDir);
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg") || filename.endsWith(".JPG")
                        || filename.endsWith(".PNG") || filename.endsWith(".JPEG"))
                    return true;
                return false;
            }
        }));

        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MyAdapter(getApplicationContext(), mImgs, R.layout.photo_grid_item, mImgDir.getAbsolutePath(), this, picNum);
        mGirdView.setAdapter(mAdapter);
        // mAdapter.notifyDataSetChanged();
        mListImageDirPopupWindow.dismiss();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSelectedImage && mSelectedImage.size() > 0) {
            mSelectedImage.clear();
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result;
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }
}
