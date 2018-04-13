package com.ypms.institution.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.AlphaPageTransformer;
import com.ypms.common.PageAdapter;
import com.ypms.common.ToolBarActivity;
import com.ypms.common.ViewPageAdapter;
import com.ypms.customWidget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/4/13.
 */

public class TeacherActivity extends ToolBarActivity {
    @BindView(R.id.view_page)
    ViewPager viewPager;
    @Override
    protected String setTittle() {
        return "师资力量";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_tacher_layout;
    }
    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, TeacherActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(false,new AlphaPageTransformer());
        List<String> pics = new ArrayList<>();
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");

        List<View> views = new ArrayList<>();
        for (int index = 0;index<pics.size();index++){
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.item_teacher_layout, null);
            CircleImageView avatar = view.findViewById(R.id.avatar);
            Picasso.with(mContext).load(pics.get(index)).into(avatar);
            views.add(view);
        }
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(views);
        viewPager.setAdapter(viewPageAdapter);
    }
}
