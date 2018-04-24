package com.ypms.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.MyDecoration;
import com.ypms.common.ToolBarActivity;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.home.model.Mechanism;
import com.ypms.mine.adapter.ActiveMineAdapter;
import com.ypms.mine.adapter.TeacherCollectAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ypms.R.id.rv;

/**
 * Created by Hero on 2018/4/24.
 */

public class TeacherCollectActivity extends ToolBarActivity {

    private TeacherCollectAdapter teacherCollectAdapter;

    @BindView(R.id.rv)
    XRecyclerView rv;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, TeacherCollectActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected String setTittle() {
        return "我收藏的老师";
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
        initEvent();
    }

    private void initEvent() {
        teacherCollectAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

            }

            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(mContext,"长安删除",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        teacherCollectAdapter = new TeacherCollectAdapter(mContext);
        rv.setAdapter(teacherCollectAdapter);
        rv.addItemDecoration(new MyDecoration(mContext,MyDecoration.VERTICAL_LIST));
        ArrayList<Mechanism> list = new ArrayList<>();
        for (int i=0;i<=10;i++){
            Mechanism mechanism = new Mechanism("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
            list.add(mechanism);
        }
        teacherCollectAdapter.setItems(list);
        teacherCollectAdapter.notifyDataSetChanged();
    }
}
