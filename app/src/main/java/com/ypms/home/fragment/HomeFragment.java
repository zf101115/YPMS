package com.ypms.home.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;
import com.ypms.R;
import com.ypms.area.model.Area;
import com.ypms.common.BannerHolderView;
import com.ypms.common.ContextUtils;
import com.ypms.common.LazyBaseFragment;
import com.ypms.common.bannerClickLinster;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.customWidget.RoundRectImageView;
import com.ypms.customWidget.ScreenPopupWindow;
import com.ypms.customWidget.SinglePopupWindow;
import com.ypms.home.Controll.HomeController;
import com.ypms.home.adapter.HomeAdapter;
import com.ypms.home.activity.MainActivity;
import com.ypms.home.adapter.HomeRankingAdapter;
import com.ypms.home.adapter.HomeTeacherAdapter;
import com.ypms.home.model.Mechanism;
import com.ypms.institution.activity.InstitutionDetailActivity;
import com.ypms.net.RestBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/2.
 */

public class HomeFragment extends LazyBaseFragment implements ScreenPopupWindow.selectItemListener {

    private XRecyclerView rv;
    private LinearLayout llSort;
    private LinearLayout llBar;
    private LinearLayout llSearchText;
    private LinearLayout llHeaderSort;
    private LinearLayout llHeaderNear;
    private LinearLayout llHeaderKind;
    private LinearLayout llHeaderSelect;
    private LinearLayout llToolNear;
    private LinearLayout llToolKind;
    private LinearLayout llToolSelect;

    private View rootView;
    private MainActivity mActivity;
    private HomeAdapter homeAdapter;
    private boolean isLoad;
    private ScreenPopupWindow screenPopupWindow;
    private SinglePopupWindow singlePopupWindow;
    private View view;
    private ViewStub viewStub;
    private ArrayList<Mechanism> list = new ArrayList<>();

    private List<String> pics = new ArrayList<>();

    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.ll_parents)
    LinearLayout llParents;
    @BindView(R.id.ll_early)
    LinearLayout llEarly;
    @BindView(R.id.ll_foreign)
    LinearLayout llForeign;
    @BindView(R.id.ll_digital)
    LinearLayout llDigital;
    @BindView(R.id.ll_special)
    LinearLayout llSpecial;
    @BindView(R.id.rl_fix_more)
    RelativeLayout rlFixMore;
    @BindView(R.id.iv_top_left)
    RoundRectImageView ivTopLeft;
    @BindView(R.id.iv_top_right)
    RoundRectImageView ivTopRight;
    @BindView(R.id.iv_bottom_left)
    RoundRectImageView ivBottomLeft;
    @BindView(R.id.iv_bottom_cent)
    RoundRectImageView ivBottomCent;
    @BindView(R.id.iv_bottom_right)
    RoundRectImageView ivBottomRight;
    @BindView(R.id.rv_horizontal)
    RecyclerView rvRanking;
    @BindView(R.id.iv_select_top)
    RoundRectImageView ivSelectTop;
    @BindView(R.id.iv_select_center)
    RoundRectImageView ivSelectCenter;
    @BindView(R.id.iv_select_bottom)
    RoundRectImageView ivSelectBottom;
    @BindView(R.id.rv_teacher)
    RecyclerView rvTeacher;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        rv = rootView.findViewById(R.id.rv);
        view = rootView.findViewById(R.id.view);
        llSort = rootView.findViewById(R.id.ll_tool_sort);
        llToolNear = rootView.findViewById(R.id.ll_near);
        llToolKind = rootView.findViewById(R.id.ll_kind);
        llToolSelect = rootView.findViewById(R.id.ll_select);
        llSort.setVisibility(View.GONE);
        llBar = rootView.findViewById(R.id.ll_search);
        llSearchText = rootView.findViewById(R.id.ll_search_text);
        llBar.getBackground().mutate().setAlpha(0);
        viewStub = rootView.findViewById(R.id.view_bac);
        rv.setPullRefreshEnabled(false);
        initView(inflater);
        initEvent();
        initDate();
        isLoad = true;
        return rootView;
    }

    private void initView(LayoutInflater inflater) {
        View header = inflater.inflate(R.layout.layout_home_header,null);
        View header2 = inflater.inflate(R.layout.layout_home_sort,null);
        llHeaderSort = header2.findViewById(R.id.ll_sort);
        llHeaderNear = header2.findViewById(R.id.ll_near);
        llHeaderSelect = header2.findViewById(R.id.ll_kind);
        llHeaderKind = header2.findViewById(R.id.ll_select);
        ButterKnife.bind(this,header);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.addHeaderView(header);
        rv.addHeaderView(header2);
        homeAdapter = new HomeAdapter(mContext);
        rv.setAdapter(homeAdapter);

        LinearLayoutManager horizontalManager = new LinearLayoutManager(getActivity());
        horizontalManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager horizontalTeacherManager = new LinearLayoutManager(getActivity());
        horizontalTeacherManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRanking.setLayoutManager(horizontalManager);
        rvTeacher.setLayoutManager(horizontalTeacherManager);
        HomeRankingAdapter homeRankingAdapter = new HomeRankingAdapter(mContext);
        HomeTeacherAdapter homeTeacherAdapter = new HomeTeacherAdapter(mContext);
        List<Mechanism> list = new ArrayList<>();
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
        homeRankingAdapter.setItems(list);
        homeTeacherAdapter.setItems(list);
        rvTeacher.setAdapter(homeTeacherAdapter);
        rvRanking.setAdapter(homeRankingAdapter);
    }


    private void initEvent() {
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
        homeAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(mContext,position+"",Toast.LENGTH_LONG).show();
                if (position>8){
                    rv.smoothScrollToPosition(2);
                }

            }

            @Override
            public void OnItemLongClick(View view, int position) {
            }
        });

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setSortVisible();
                setSearchAlpha();
            }
        });
        llToolNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getPermission(new PermissionCall() {
//                    @Override
//                    public void success(List<Integer> list) {
//                        Toast.makeText(mContext,list.size()+"成功数目",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void fail(List<Integer> list) {
//                        Toast.makeText(mContext,list.size()+"失败数目",Toast.LENGTH_SHORT).show();
//
//                    }
//                },Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);

                ContextUtils.showPopupWindow(screenPopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });
        llToolKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.showPopupWindow(singlePopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });

        llToolSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtils.showPopupWindow(singlePopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });
        llHeaderNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRvPosition();
                ContextUtils.showPopupWindow(screenPopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });


        llHeaderKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRvPosition();
                ContextUtils.showPopupWindow(singlePopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });

        llHeaderSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRvPosition();
                ContextUtils.showPopupWindow(singlePopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });

        screenPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                viewStub.setVisibility(View.GONE);
            }
        });
        singlePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                viewStub.setVisibility(View.GONE);
            }
        });

        llParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstitutionDetailActivity.startActivity(mContext);
            }
        });

    }

    private void setRvPosition() {
        int firstItem = rv.getChildLayoutPosition(rv.getChildAt(0));
        int movePosition = 2 - firstItem;
        if (movePosition >= 0 && movePosition < rv.getChildCount()) {
            int top = rv.getChildAt(movePosition).getTop();
            rv.smoothScrollBy(0, top);
        }
    }

    private void setSearchAlpha() {

    }

    private void setSortVisible() {
        int[] tabSort = new int[2];
        int[] bannerLocation = new int[2];
        banner.getLocationOnScreen(bannerLocation);
        llHeaderSort.getLocationOnScreen(tabSort);
        int searchHeight = llBar.getMeasuredHeight();
        int topY = tabSort[1]-getNotificationHigh()-searchHeight;
        llSort.setVisibility(topY<=0&&rv.getChildCount()>1&&bannerLocation[1]-getNotificationHigh()<0?View.VISIBLE:View.GONE);
        int endOffset =260;
        if (Math.abs(bannerLocation[1]-getNotificationHigh())<=0){
            llBar.getBackground().mutate().setAlpha(0);
            llSearchText.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_edit_white));
        }else if (rv.getChildLayoutPosition(rv.getChildAt(0))>1||Math.abs(bannerLocation[1]-getNotificationHigh())>endOffset){
            llBar.getBackground().mutate().setAlpha(255);
            llSearchText.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_grey_grey_radius));
        }else if (Math.abs(bannerLocation[1]-getNotificationHigh())>0&&Math.abs(bannerLocation[1]-getNotificationHigh())<endOffset){
            float precent = (float) (Math.abs(bannerLocation[1]-getNotificationHigh())) / endOffset;
            int alpha = Math.round(precent * 255);
            llBar.getBackground().mutate().setAlpha(alpha);
            llSearchText.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_edit_white));
        }
    }


    /**
     * 获取通知栏的高度
     */
    private int getNotificationHigh() {
        Rect outRect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;
    }
    @Override
    protected void lazyLoad() {
        if (null != mActivity && isVisible && !isLoad) {
            initDate();
        }
    }

    private void initDate() {
        pics.add("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg");
        pics.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg");
        banner.setPages(new CBViewHolderCreator<BannerHolderView>() {
            @Override
            public BannerHolderView createHolder() {
                return new BannerHolderView(new bannerClickLinster <String>() {
                    @Override
                    public void OnItemClick(String s, int position) {

                    }
                });
            }
        },pics)
//        .setPointViewVisible(true)
        .startTurning(4000);

//        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(banner);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivTopLeft);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivTopRight);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivSelectTop);
        Picasso.with(mContext).load("http://z.newstaredu.cn/news/2017/2017-10/%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD55.jpeg").into(ivSelectBottom);
        Picasso.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg").into(ivBottomCent);
        Picasso.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg").into(ivSelectCenter);
        Picasso.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg").into(ivBottomLeft);
        Picasso.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522734328&di=5067351a86cf85b3337c7641ad55279a&imgtype=jpg&er=1&src=http%3A%2F%2Fsem.g3img.com%2Fg3img%2Fweiwuguoji%2F20140711161058_41758.jpg").into(ivBottomRight);

//        HomeController homeController = new HomeController();
//        homeController.getHomeData(this, new HomeController.HomeDataListener() {
//            @Override
//            public void onComplete(Mechanism mechanismPage) {
//                for (int i=0;i<=10;i++){
//                    Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
//                    list.add(mechanism);
//                }
//                homeAdapter.setItems(list);
//                homeAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailed(RestBase resetBody) {
//                Toast.makeText(mContext,resetBody.getDetail(),Toast.LENGTH_SHORT).show();
//
//            }
//        });

        for (int i=0;i<=10;i++){
            Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
            list.add(mechanism);
        }
        homeAdapter.setItems(list);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    @Override
    public void onStart() {
        super.onStart();
        banner.startTurning(4000);
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopTurning();
    }
}
