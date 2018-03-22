package com.ypms.customWidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ypms.R;


/**
 * Created by Hero on 2018/3/16.
 * 自定义Toast弹窗
 */

public class CustomToast {
    public static final int LONG=3000;
    public static final int SHORT=1500;


    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToastAtCenter(Context mContext,String text,int imgId,int duration){
        if (mContext==null)return;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_custom_toast_center, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_toast);
        ImageView img = (ImageView) view.findViewById(R.id.img_toast);
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            tv.setText(text);
            img.setImageResource(imgId);
        } else {
            mToast = new Toast(mContext);
            tv.setText(text);
            img.setImageResource(imgId);
        }
        mHandler.postDelayed(r, duration);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setView(view);
        mToast.show();
    }

    public static void showToastAtTop(Context mContext,String text,int imgId,int duration){

        if(null == mContext){
            return;//某些情况下context可能会为空
        }
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_custom_toast_top, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_toast_top);


        Drawable drawable = mContext.getResources().getDrawable(imgId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        mHandler.removeCallbacks(r);
        if (mToast != null) {
            tv.setText(text);
            tv.setCompoundDrawables(drawable,null,null,null);
        } else {
            mToast = new Toast(mContext);
            tv.setText(text);
            tv.setCompoundDrawables(drawable,null,null,null);
        }

        mHandler.postDelayed(r, duration);
        mToast.setGravity(Gravity.FILL,0,0);
        mToast.setView(view);
        // mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();


    }

}
