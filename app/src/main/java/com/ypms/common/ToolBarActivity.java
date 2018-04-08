package com.ypms.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.ypms.R;
import com.ypms.customWidget.CustomToast;
import com.ypms.net.RestBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by Hero on 2018/3/2.
 */

public abstract class ToolBarActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {

    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleSubject.onNext(ActivityEvent.CREATE);
        supportRequestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        mContext = this;
        setContentView(getLayoutResource());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResource();

    protected void showRestError(RestBase restBase){

        CustomToast.showToastAtCenter(mContext,restBase.getDetail(), R.drawable.custom_toast_fail,CustomToast.SHORT);
    }


    /**
     * 动态权限申请部分
     */
    private PermissionCall permissionCall;

    public void getPermission(PermissionCall permission, String... permissions) {
        this.permissionCall = permission;
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        if (Build.VERSION.SDK_INT >= 23) {
            if (findDeniedPermissions(this, permissions).size() > 0) {
                PermissionGen.with(this).addRequestCode(100).permissions(permissions).request();
            } else {
                permissionCall.success(list);
            }
        } else {
            permissionCall.success(list);
        }
    }

    public int getNotificationHigh() {
        Rect outRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        List<Integer> successList = new ArrayList<>();
        List<Integer> failList = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                failList.add(i);
            }else {
                successList.add(i);
            }
        }
        if (successList.size()>0)
            permissionCall.success(successList);
        if (failList.size()>0)
            permissionCall.fail(failList);
    }
    /**
     * 实现RxLifeCycle部分。。。
     */
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }


    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}

