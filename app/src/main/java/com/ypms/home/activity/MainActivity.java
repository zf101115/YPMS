package com.ypms.home.activity;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ypms.R;
import com.ypms.comment.activity.CommentAddActivity;
import com.ypms.common.ContextUtils;
import com.ypms.common.PageAdapter;
import com.ypms.common.PermissionCall;
import com.ypms.common.ToolBarActivity;
import com.ypms.customWidget.CustomToast;
import com.ypms.customWidget.CustomViewPage;
import com.ypms.home.fragment.DiscoverFragment;
import com.ypms.home.fragment.HomeFragment;
import com.ypms.home.fragment.MeFragment;
import com.ypms.photopick.imageloader.PhotoPickActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ToolBarActivity {

    @BindView(R.id.view_page)
    CustomViewPage viewPage;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_discover)
    LinearLayout llDiscover;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_discover)
    ImageView ivDiscover;
    @BindView(R.id.tv_discover)
    TextView tvDiscover;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    public AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mlocationClient = null;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected String setTittle() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initWidget();
        checkPhotoPermission();
    }

    private void checkPhotoPermission(){
        getPermission(new PermissionCall() {
            @Override
            public void success(List<Integer> list) {
                //TODO:（全部）申请成功的处理
                iniLocation();
            }
            @Override
            public void fail(List<Integer> list) {
                CustomToast.showToastAtCenter(mContext,"没有权限无法获取附近信息", R.drawable.custom_toast_fail,CustomToast.SHORT);
            }
        }, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void iniLocation() {
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
//设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        Log.e("==城区信息",amapLocation.getDistrict());
                        Log.e("==城市信息",amapLocation.getCity());
                        Log.e("==城市编码",amapLocation.getCityCode());
                        Log.e("==地区编码",amapLocation.getAdCode());
                    } else {
                        CustomToast.showToastAtCenter(mContext,"定位失败", R.drawable.custom_toast_fail,CustomToast.SHORT);
                    }
                }
            }
        });
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();

    }

    private void initWidget() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), getFragments());
        viewPage.setAdapter(pageAdapter);
        viewPage.setOffscreenPageLimit(2);
        viewPage.setIsScrollAble(false);
        resetTabBg(tvHome,ivHome);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPage.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getFragments() {
        HomeFragment homeFragment = new HomeFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        MeFragment meFragment = new MeFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(discoverFragment);
        fragments.add(meFragment);
//        Fragment[] fragments = new Fragment[]{homeFragment, discoverFragment, meFragment};
        return fragments;
    }


    @OnClick(R.id.ll_home)
    public void homeClick() {
        resetTabBg(tvHome,ivHome);
        viewPage.setCurrentItem(0, false);
    }

    @OnClick(R.id.ll_discover)
    public void discoverClick() {
        resetTabBg(tvDiscover,ivDiscover);
        viewPage.setCurrentItem(1, false);

    }

    @OnClick(R.id.ll_mine)
    public void mineClick() {
        resetTabBg(tvMine,ivMine);
        viewPage.setCurrentItem(2, false);
    }

    private void resetTabBg(TextView tv, ImageView iv) {
        tvHome.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        tvDiscover.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        tvMine.setTextColor(ContextUtils.getColor(mContext, R.color.color_grey_99));
        ivDiscover.setSelected(false);
        ivHome.setSelected(false);
        ivMine.setSelected(false);
        tv.setTextColor(ContextUtils.getColor(mContext, R.color.colorPrimary));
        iv.setSelected(true);
    }

}
