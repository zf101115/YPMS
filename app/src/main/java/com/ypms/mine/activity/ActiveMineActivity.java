package com.ypms.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.ToolBarActivity;
import com.ypms.course.adapter.BulkAdapter;
import com.ypms.home.model.Mechanism;
import com.ypms.institution.activity.InstitutionActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/24.
 */

public class ActiveMineActivity extends ToolBarActivity {

    @BindView(R.id.rv)
    XRecyclerView rv;


    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, ActiveMineActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected String setTittle() {
        return "我报名的活动";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_single_rv;
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
        BulkAdapter bulkAdapter = new BulkAdapter(mContext);
        rv.setAdapter(bulkAdapter);
        ArrayList<Mechanism> list = new ArrayList<>();
        for (int i=0;i<=10;i++){
            Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
            list.add(mechanism);
        }
        bulkAdapter.setItems(list);
        bulkAdapter.notifyDataSetChanged();
    }
}
