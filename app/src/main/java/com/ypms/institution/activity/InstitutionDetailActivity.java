package com.ypms.institution.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.common.ContextUtils;
import com.ypms.common.ToolBarActivity;
import com.ypms.course.activity.CourseBulkActivity;
import com.ypms.customWidget.AppointmentDialog;
import com.ypms.customWidget.CircleImageView;
import com.ypms.customWidget.ObservableScrollView;
import com.ypms.customWidget.RoundRectImageView;
import com.ypms.customWidget.StartView;
import com.ypms.home.adapter.HomeRankingAdapter;
import com.ypms.home.model.Mechanism;
import com.ypms.institution.adapter.TeacherAdapter;
import com.ypms.order.activity.OrderSubmitActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hero on 2018/4/5.
 */

public class InstitutionDetailActivity extends ToolBarActivity {

    private int[] tabReview = new int[2];
    private int[] tabClass= new int[2];
    private boolean isActiveOpen;
    private int toolHeight;
    private int defautHeight;

    @BindView(R.id.tv_tab_ins)
    TextView tvTabIns;
    @BindView(R.id.view_tab_ins)
    View viewTabIns;
    @BindView(R.id.ll_tab_ins)
    LinearLayout llTabIns;
    @BindView(R.id.tv_tab_class)
    TextView tvTabClass;
    @BindView(R.id.view_tab_class)
    View viewTabClass;
    @BindView(R.id.ll_tab_class)
    LinearLayout llTabClass;
    @BindView(R.id.tv_tab_review)
    TextView tvTabReview;
    @BindView(R.id.view_tab_review)
    View viewTabReview;
    @BindView(R.id.ll_tab_review)
    LinearLayout llTabReview;
    @BindView(R.id.tv_mark_left)
    TextView tvMarkLeft;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.ll_active_content)
    LinearLayout llActive;
    @BindView(R.id.ll_bulk)
    LinearLayout llBulk;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.rv_teacher)
    RecyclerView rvTeacher;
    @BindView(R.id.rv_ad)
    RecyclerView rvAd;
    @BindView(R.id.scroll)
    ObservableScrollView scroll;
    @BindView(R.id.ll_class)
    LinearLayout llClass;
    @BindView(R.id.ll_review)
    LinearLayout llReview;
    @BindView(R.id.ll_review_content)
    LinearLayout llReviewContent;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.iv_menu_left)
    ImageView ivMenuLeft;
    @BindView(R.id.iv_menu_right)
    ImageView ivMenuRight;
    @BindView(R.id.ll_honor)
    LinearLayout llHonor;

    List<Mechanism> list;

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, InstitutionDetailActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_intitution_detail;
    }

    @Override
    protected String setTittle() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivPic);
        LinearLayoutManager horizontalTeacherManager = new LinearLayoutManager(mContext);
        horizontalTeacherManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext);
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTeacher.setLayoutManager(horizontalTeacherManager);
        rvAd.setLayoutManager(horizontalManager);
        TeacherAdapter teacherAdapter = new TeacherAdapter(mContext);
        HomeRankingAdapter homeRankingAdapter = new HomeRankingAdapter(mContext);
        list = new ArrayList<>();
        Mechanism mechanism = new Mechanism("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        Mechanism mechanism1 = new Mechanism("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        Mechanism mechanism2 = new Mechanism("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        Mechanism mechanism3 = new Mechanism("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        Mechanism mechanism4 = new Mechanism("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        list.add(mechanism);
        list.add(mechanism1);
        list.add(mechanism2);
        list.add(mechanism3);
        list.add(mechanism4);
        teacherAdapter.setItems(list);
        homeRankingAdapter.setItems(list);
        rvTeacher.setAdapter(teacherAdapter);
        rvAd.setAdapter(homeRankingAdapter);
        llTab.setVisibility(View.VISIBLE);
        ivMenuLeft.setVisibility(View.VISIBLE);
        ivMenuRight.setVisibility(View.VISIBLE);
        initData();
        initEvent();
        /**
         * post一个任务到队尾，来获取控件坐标，防止获取高度为0
         */
        llClass.post(new Runnable() {
            @Override
            public void run() {
                llClass.getLocationOnScreen(tabClass);
                llReview.getLocationOnScreen(tabReview);
                toolHeight = toolbar.getMeasuredHeight() + getNotificationHigh();
            }
        });
    }

    private void initData() {
        for (int index = 0;index<2;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_single_text,null);
            llHonor.addView(itemView);
        }

        for (int index=0; index<2;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_active_layout,null);
            RoundRectImageView ivActive = itemView.findViewById(R.id.iv_active_pic);
            Picasso.with(mContext).load(list.get(index).getTitle()).into(ivActive);
            itemView.setTag(index);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    clickView((Integer) v.getTag());
                    AppointmentDialog appointmentDialog = new AppointmentDialog(mContext);
                    appointmentDialog.show();
                }
            });
            llActive.addView(itemView);
        }
        for (int index=0; index<2;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_bulk_layout,null);
            RoundRectImageView ivBulk = itemView.findViewById(R.id.iv_bulk);
            TextView tvClassPay = itemView.findViewById(R.id.tv_class_pay);
            Picasso.with(mContext).load(list.get(index).getTitle()).into(ivBulk);
            itemView.setTag(index);
            tvClassPay.setTag(index);
            tvClassPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderSubmitActivity.startActivity(mContext);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    clickView((Integer) v.getTag());
//                }
//            });
            llBulk.addView(itemView);
        }

        for (int index=0; index<2;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_bulk_layout,null);
            RoundRectImageView ivBulk = itemView.findViewById(R.id.iv_bulk);
            TextView tvPay = itemView.findViewById(R.id.tv_class_pay);
            TextView tvClassNum = itemView.findViewById(R.id.tv_class_num);
            tvClassNum.setVisibility(View.GONE);
            tvPay.setText("立即抢购");
            tvPay.setTag(index);
            Picasso.with(mContext).load(list.get(index).getTitle()).into(ivBulk);
            itemView.setTag(index);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    clickView((Integer) v.getTag());
                }
            });
            tvPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderSubmitActivity.startActivity(mContext);
                }
            });
            llOther.addView(itemView);
        }

        for (int index=0; index<5 ;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_review_layout,null);
            CircleImageView ivAvatar = itemView.findViewById(R.id.iv_review_pic);
            StartView startView = itemView.findViewById(R.id.start);
            startView.setStarMark(3.6f);
            Picasso.with(mContext).load(list.get(index).getTitle()).into(ivAvatar);
            itemView.setTag(index);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    clickView((Integer) v.getTag());
                    CommentDetailActivity.startActivity(mContext);
                }
            });
            llReviewContent.addView(itemView);
        }
    }

    private void initEvent() {
        scroll.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t) {
                if (isActiveOpen)
                    defautHeight = toolHeight-(llActive.getMeasuredHeight()/5)*3;
                else
                    defautHeight = toolHeight;
                if (t>tabReview[1]-defautHeight-10||ContextUtils.isViewInScreen(rvAd)){
                    setTabStatus(2);
                }else if (t>=(tabClass[1]-defautHeight)&&t<(tabReview[1]-defautHeight)&&!ContextUtils.isViewInScreen(rvAd)){
                    setTabStatus(1);
                }else if (t<tabClass[1]-defautHeight&&!ContextUtils.isViewInScreen(rvAd)){
                    setTabStatus(0);
                }
            }
        });

    }

    @OnClick(R.id.ll_tab_class)
    public void tabClassClick() {
        scrollToPosition(isActiveOpen?tabClass[1]-toolHeight+(llActive.getMeasuredHeight()/5)*3:tabClass[1] - toolHeight);
    }

    @OnClick(R.id.ll_tab_ins)
    public void tabInsClick() {
        scrollToPosition(0);
    }

    @OnClick(R.id.ll_tab_review)
    public void tabReviewClick() {
        scrollToPosition(isActiveOpen?tabReview[1]-toolHeight+(llActive.getMeasuredHeight()/5)*3:tabReview[1] - toolHeight);
    }
    @OnClick(R.id.ll_other_more)
    public void otherMoreClick(){

    }
    @OnClick(R.id.ll_teacher)
    public void llTeacherClick(){
        TeacherActivity.startActivity(mContext);
    }
    @OnClick(R.id.ll_class)
    public void llClassClick(){
        CourseBulkActivity.startActivity(mContext);
    }
    @OnClick(R.id.ll_stretch)
    public void stretchClick(){
        if (llActive.getChildCount()>2){
            llActive.removeViews(2,3);
            isActiveOpen = false;
        }else {
        for (int index=2; index<5;index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_active_layout,null);
            RoundRectImageView ivActive = itemView.findViewById(R.id.iv_active_pic);
            Picasso.with(mContext).load(list.get(index).getTitle()).into(ivActive);
            itemView.setTag(index);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppointmentDialog appointmentDialog = new AppointmentDialog(mContext);
                    appointmentDialog.show();
                }
            });
            llActive.addView(itemView);
        }
        isActiveOpen = true;
        }
    }

    public void scrollToPosition(int y) {
        ObjectAnimator yTranslate = ObjectAnimator.ofInt(scroll, "scrollY", y);
        AnimatorSet animators = new AnimatorSet();
        animators.setDuration(600L);
        animators.play(yTranslate);
        animators.start();
    }

    private void setTabStatus(int index){
        switch (index){
            case 0:
                tvTabClass.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                tvTabIns.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                tvTabReview.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                viewTabClass.setVisibility(View.GONE);
                viewTabIns.setVisibility(View.VISIBLE);
                viewTabReview.setVisibility(View.GONE);
                break;
            case 1:
                tvTabClass.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                tvTabIns.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                tvTabReview.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                viewTabClass.setVisibility(View.VISIBLE);
                viewTabIns.setVisibility(View.GONE);
                viewTabReview.setVisibility(View.GONE);
                break;
            case 2:
                tvTabClass.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                tvTabIns.setTextColor(mContext.getResources().getColor(R.color.text_search_grey));
                tvTabReview.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                viewTabClass.setVisibility(View.GONE);
                viewTabIns.setVisibility(View.GONE);
                viewTabReview.setVisibility(View.VISIBLE);
                break;
            default:break;
        }
    }
}
