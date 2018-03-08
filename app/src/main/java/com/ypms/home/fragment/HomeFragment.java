package com.ypms.home.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypms.R;
import com.ypms.common.LazyBaseFragment;
import com.ypms.common.recycleView.RecyclerItemClickListener;
import com.ypms.home.HomeAdapter;
import com.ypms.home.activity.MainActivity;
import com.ypms.home.model.Mechanism;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hero on 2018/3/2.
 */

public class HomeFragment extends LazyBaseFragment {

    private XRecyclerView rv;
    private LinearLayout llSort;
    private LinearLayout llBar;
    private LinearLayout llHeaderSort;

    private View rootView;
    private boolean isCreatView;
    private MainActivity mActivity;
    private HomeAdapter homeAdapter;
    private ArrayList<Mechanism> list = new ArrayList<>();

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
        llSort = rootView.findViewById(R.id.ll_tool_sort);
        llBar = rootView.findViewById(R.id.ll_bar);
        rv.setPullRefreshEnabled(false);
        initView(inflater);
        initEvent();
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
        for (int i=0;i<=10;i++){
            Mechanism mechanism = new Mechanism(i+"古墩路因么培训机构");
            list.add(mechanism);
        }
        homeAdapter = new HomeAdapter(mContext);
        homeAdapter.setItems(list);
        rv.setAdapter(homeAdapter);
    }

    private void initEvent() {
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
        if (null != mActivity && isCreatView && isVisible) {
            initDate();
        }
    }

    private void initDate() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
