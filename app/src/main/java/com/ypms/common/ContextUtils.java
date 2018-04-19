package com.ypms.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hero on 2018/3/2.
 * 该工具类中的方法需要传入context参数
 */

public class ContextUtils {


    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static void showPopupWindow(PopupWindow pop,View view){
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            int y = location[1] ;
            pop.showAtLocation(view, Gravity.NO_GRAVITY,0,y);
//        }else {
//            pop.showAsDropDown(view);
//        }
    }

    /**
     * 判断当前view是否显示在手机屏幕中,此方法仅供上下滚动判断，若判断左右需更改判断条件
     * @param mView
     * @return
     */
    public static boolean isViewInScreen(View mView){
        if (mView!=null&&mView.isShown()) {
            DisplayMetrics displayMetrics = mView.getContext().getResources().getDisplayMetrics();
            int displayWidth = displayMetrics.heightPixels;
            Rect rect = new Rect();
            mView.getGlobalVisibleRect(rect);
            if ((rect.top > 0) && (rect.top <= displayWidth)) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * @param path,给定宽度width
     * @return bitmap
     */
    //压缩图片大小
    public static Bitmap compressImage(String path, float width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //Bitmap bmp = BitmapFactory.decodeFile(path, options); //此时返回bm为空
        BitmapFactory.decodeFile(path, options);
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = (int) (options.outWidth / width);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;


        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        int degree = getBitmapDegree(path);
        if (degree != 0) {
            bmp = rotateBitmapByDegree(bmp, degree);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (null != bmp) {
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, baos);
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
            bmp = BitmapFactory.decodeStream(isBm, null, null);
        }
        return bmp;
    }


    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }



    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 是否有SD卡
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() == 0;
    }

}
