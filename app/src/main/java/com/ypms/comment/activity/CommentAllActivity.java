package com.ypms.comment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.comment.adapter.CommentAllAdapter;
import com.ypms.common.ToolBarActivity;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentAllActivity extends ToolBarActivity {

    private CommentAllAdapter commentAllAdapter;
    @BindView(R.id.rv)
    XRecyclerView rv;

    @Override
    protected String setTittle() {
        return "全部点评";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_single_rv;
    }

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, CommentAllActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initWidget();
        initEvent();


    }

    private void initEvent() {
        commentAllAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                CommentDetailActivity.startActivity(mContext);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void initWidget() {

        List<String> pics = new ArrayList<>();
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        commentAllAdapter = new CommentAllAdapter(mContext);
        rv.setAdapter(commentAllAdapter);
        commentAllAdapter.setPics(pics);
        ArrayList<Mechanism> list = new ArrayList<>();
        for (int i=0;i<6;i++){
            Mechanism mechanism = new Mechanism(pics.get(i));
            list.add(mechanism);
        }
        commentAllAdapter.setItems(list);
        commentAllAdapter.notifyDataSetChanged();
    }
}
