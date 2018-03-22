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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.area.model.Area;
import com.ypms.common.ContextUtils;
import com.ypms.common.LazyBaseFragment;
import com.ypms.common.ToolBarActivity;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.customWidget.ScreenPopupWindow;
import com.ypms.home.Controll.HomeController;
import com.ypms.home.HomeAdapter;
import com.ypms.home.activity.MainActivity;
import com.ypms.home.model.Mechanism;
import com.ypms.home.model.MechanismPage;
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
    private LinearLayout llHeaderSort;

    private View rootView;
    private boolean isCreatView;
    private MainActivity mActivity;
    private HomeAdapter homeAdapter;
    private boolean isLoad;
    private ScreenPopupWindow screenPopupWindow;
    private View view;
    private ViewStub viewStub;
    private ArrayList<Mechanism> list = new ArrayList<>();

    @BindView(R.id.banner)
    ConvenientBanner banner;


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
        llBar = rootView.findViewById(R.id.ll_bar);
        viewStub = rootView.findViewById(R.id.view_bac);
        rv.setPullRefreshEnabled(false);
        initView(inflater);
        initEvent();
        initDate();
        isLoad = true;
        isCreatView = true;
        return rootView;
    }

    private void initView(LayoutInflater inflater) {
        View header = inflater.inflate(R.layout.layout_home_header,null);
        View header2 = inflater.inflate(R.layout.layout_home_sort,null);
        llHeaderSort = header2.findViewById(R.id.ll_sort);
        ButterKnife.bind(this,header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.addHeaderView(header);
        rv.addHeaderView(header2);
        homeAdapter = new HomeAdapter(mContext);
        rv.setAdapter(homeAdapter);
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
            }
        });
        llSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContextUtils.showPopupWindow(screenPopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });
        llHeaderSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstItem = rv.getChildLayoutPosition(rv.getChildAt(0));
                int movePosition = 2 - firstItem;
                if (movePosition >= 0 && movePosition < rv.getChildCount()) {
                    int top = rv.getChildAt(movePosition).getTop();
                    rv.smoothScrollBy(0, top);
                }
                ContextUtils.showPopupWindow(screenPopupWindow,HomeFragment.this.view);
                viewStub.setVisibility(View.VISIBLE);
            }
        });

        screenPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                viewStub.setVisibility(View.GONE);
            }
        });

    }
    private void setSortVisible() {
        int[] tabSort = new int[2];
        llHeaderSort.getLocationOnScreen(tabSort);
        int toolBarHeight = llBar.getMeasuredHeight();
        int topY = tabSort[1]-toolBarHeight-getNotificationHigh();
        llSort.setVisibility(topY<=0?View.VISIBLE:View.GONE);
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
        HomeController homeController = new HomeController();
        homeController.getHomeData(this, new HomeController.HomeDataListener() {
            @Override
            public void onComplete(Mechanism mechanismPage) {
                for (int i=0;i<=10;i++){
                    Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
                    list.add(mechanism);
                }
                homeAdapter.setItems(list);
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(RestBase resetBody) {
                Toast.makeText(mContext,resetBody.getDetail(),Toast.LENGTH_SHORT).show();

            }
        });

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
}
