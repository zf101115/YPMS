package com.ypms.common;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

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

}
