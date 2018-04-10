package com.ypms.institution.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.ToolBarActivity;
import com.ypms.home.adapter.HomeAdapter;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/9.
 */

public class InstitutionActivity extends ToolBarActivity {

    private HomeAdapter homeAdapter;


    @BindView(R.id.ll_near)
    LinearLayout llNear;
    @BindView(R.id.ll_kind)
    LinearLayout llKind;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.rv)
    XRecyclerView rv;

    @Override
    protected String setTittle() {
        return "机构";
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
}
