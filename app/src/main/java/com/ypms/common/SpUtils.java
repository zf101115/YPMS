package com.ypms.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hero on 2018/5/2.
 */

public class SpUtils {
    /**
     * 参数Key值
     */
    public static final String SP_VERTIFY_TIME = "SpUtils.SP_VERTIFY_TIME";//验证码倒计时时间

    protected Context mContext;
    private final String SP_NAME ="com.ylms.utils.SP";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public SpUtils(Context context){
        this.mContext = context.getApplicationContext();
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setLong(String key,long value){
        editor.putLong(key,value);
        editor.commit();
    }

    public long getLong(String key,long def){
        return sp.getLong(key,def);
    }

}
