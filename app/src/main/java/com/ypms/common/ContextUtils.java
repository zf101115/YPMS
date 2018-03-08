package com.ypms.common;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by Hero on 2018/3/2.
 * 该工具类中的方法需要传入context参数
 */

public class ContextUtils {


    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
//        if (version >= 23) {
            return ContextCompat.getColor(context, id);
//        } else {
//            return context.getResources().getColor(id);
//        }
    }

}
