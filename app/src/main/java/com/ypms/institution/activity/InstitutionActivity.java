package com.ypms.institution.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.area.model.Area;
import com.ypms.common.ContextUtils;
import com.ypms.common.ToolBarActivity;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.course.activity.CourseBulkActivity;
import com.ypms.customWidget.ScreenPopupWindow;
import com.ypms.customWidget.SinglePopupWindow;
import com.ypms.home.adapter.HomeAdapter;
import com.ypms.home.fragment.HomeFragment;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/9.
 */

public class InstitutionActivity extends ToolBarActivity implements ScreenPopupWindow.selectItemListener {

    private HomeAdapter homeAdapter;
    private ScreenPopupWindow screenPopupWindow;
    private SinglePopupWindow singlePopupWindow;

    @BindView(R.id.ll_near)
    LinearLayout llNear;
    @BindView(R.id.ll_kind)
    LinearLayout llKind;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.view)
    View view;

    @Override
    protected String setTittle() {
        return "机构";
    }

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, InstitutionActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_institution_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rv.setPullRefreshEnabled(true);

        initData();
        initEvent();
        initPopup();
    }

    private void initEvent() {
        homeAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                InstitutionDetailActivity.startActivity(mContext);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void initPopup() {
        List<Area> areaList = new ArrayList<>();
        List<Area> Lists = new ArrayList<>();
        Area are = new Area("附近(智能筛选)",1);
        Area are1 = new Area("500米",2);
        Area are2 = new Area("1000米",3);
        Area are3 = new Area("2000米",4);
        Lists.add(are);
        Lists.add(are1);
        Lists.add(are2);
        Lists.add(are3);
        Area area = new Area("附近",5);
        Area area1 = new Area("西湖区",6);
        Area area2 = new Area("上城区",7);
        Area area3 = new Area("下城区",8);
        Area area4 = new Area("拱墅区",9);
        Area area5 = new Area("滨江区",10);
        Area area6 = new Area("余杭区",11);
        Area area7 = new Area("萧山区",12);
        areaList.add(area);
        areaList.add(area1);
        areaList.add(area2);
        areaList.add(area3);
        areaList.add(area4);
        areaList.add(area5);
        areaList.add(area6);
        areaList.add(area7);

        screenPopupWindow = new ScreenPopupWindow(mContext,areaList,Lists);
        singlePopupWindow = new SinglePopupWindow(mContext,areaList);
        screenPopupWindow.setSelectItemListener(this);
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(mContext);
        rv.setAdapter(homeAdapter);
        ArrayList<Mechanism> list = new ArrayList<>();
        for (int i=0;i<=10;i++){
            Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
            list.add(mechanism);
        }
        homeAdapter.setItems(list);
        homeAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ll_near)
    public void nearClick(){
        ContextUtils.showPopupWindow(screenPopupWindow,view);
    }

    @OnClick(R.id.ll_kind)
    public void kindClick(){
        ContextUtils.showPopupWindow(singlePopupWindow,view);
    }

    @Override
    public void onSelect(Area area) {
        screenPopupWindow.dismiss();
    }

    @Override
    public void onLevelSelect(Area area) {
        List<Area> Lists = new ArrayList<>();
        Area are = new Area("湖滨商圈",15);
        Area are1 = new Area("西湖文化广场",12);
        Area are2 = new Area("武林商圈",13);
        Area are3 = new Area("滨江高教园",14);
        Lists.add(are);
        Lists.add(are1);
        Lists.add(are2);
        Lists.add(are3);
        screenPopupWindow.setListData(Lists);
    }
}
