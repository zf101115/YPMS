package com.ypms.customWidget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Hero on 2018/3/2.
 */

public class CustomViewPage extends ViewPager {
    private boolean isScrollable = true;
    public CustomViewPage(Context context) {
        super(context);
    }

    public CustomViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsScrollAble(boolean isScrollAble){
        this.isScrollable = isScrollAble;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }
}
