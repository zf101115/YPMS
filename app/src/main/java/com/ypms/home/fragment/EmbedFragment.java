package com.ypms.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.BaseFragment;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.course.adapter.ReviewInfoAdapter;
import com.ypms.home.model.Mechanism;
import com.ypms.institution.activity.InstitutionActiveActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/12.
 */

public class EmbedFragment extends BaseFragment {
    private View containerView;
    private ReviewInfoAdapter reviewInfoAdapter;
    @BindView(R.id.rv)
    XRecyclerView rv;
    private String type;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,containerView);
        initData();
        initEvent();
        return containerView;
    }

    private void initEvent() {
        reviewInfoAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                InstitutionActiveActivity.startActivity(mContext);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        List<Mechanism> list = new ArrayList<>();
        for (int i = 0;i<3;i++){
            Mechanism mechanism = new Mechanism("i");
            list.add(mechanism);
        }
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
        reviewInfoAdapter = new ReviewInfoAdapter(mContext);
        reviewInfoAdapter.setItems(list);
        reviewInfoAdapter.setPics(pics);
        rv.setAdapter(reviewInfoAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_rv;
    }
}
