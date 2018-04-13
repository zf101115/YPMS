package com.ypms.comment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.comment.adapter.CommentAdapter;
import com.ypms.common.ToolBarActivity;
import com.ypms.course.adapter.ReviewPicAdapter;
import com.ypms.customWidget.CircleImageView;
import com.ypms.customWidget.NoScrollGridView;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/13.
 */

public class CommentDetailActivity extends ToolBarActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_pic)
    LinearLayout ivPic;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.grid_pic)
    NoScrollGridView gridPic;
    @BindView(R.id.ll_comment_header)
    LinearLayout llCommentHeader;
    @BindView(R.id.avatar)
    CircleImageView avatar;


    private View headerView;
    private XRecyclerView rv;

    @Override
    protected String setTittle() {
        return "点评详情";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_active_layout;
    }

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, CommentDetailActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_layout, null);
        rv = findViewById(R.id.rv);
        ButterKnife.bind(this, headerView);
        llCommentHeader.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(avatar);
        initData();
    }

    private void initData() {
        ReviewPicAdapter reviewPicAdapter = new ReviewPicAdapter(mContext);
        List<String> pics = new ArrayList<>();
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        reviewPicAdapter.setItems(pics);
        gridPic.setAdapter(reviewPicAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.addHeaderView(headerView);

        CommentAdapter commentAdapter = new CommentAdapter(mContext);
        rv.setAdapter(commentAdapter);
        ArrayList<Mechanism> list = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            Mechanism mechanism = new Mechanism("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
            list.add(mechanism);
        }
        commentAdapter.setItems(list);
        commentAdapter.notifyDataSetChanged();
    }
}
