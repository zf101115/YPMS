package com.ypms.net;

/**
 * Created by Hero on 2018/3/1.
 */

public class StatusCode {
    public static final int NETWORK_ERROR = -1;//网络异常
    public static final int UNEXPECTED_ERROR = -2;//不可预期的一些问题
    public static final int TIME_OUT_ERROR = -3;//连接超时


    public static final String NETWORK_ERROR_TIPS = "网络异常，请检查网络";

    public static final String SOCKET_TIME_OUT = "连接超时，请检查网络重试";

    public static final String SERVER_ERROR_TIPS = "无法访问";

    public static final String CLIENT_ERROR_TIPS = "访问数据出错";

    public static final String AUTH_ERROR_TIPS = "未认证,重新登录再重试";

    public static final String JSON_ERROR_TIPS = "数据解析错误";

    public static final String UNEXPECTED_ERROR_TIPS = "数据异常，请保持网络环境良好并重试";

    public static final String SERVER_LIMITED = "账号访问被限制，请联系客服";
}
