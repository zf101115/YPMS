package com.ypms.customWidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ypms.R;
import com.ypms.area.adapter.PopAreaAdapter;
import com.ypms.area.model.Area;
import com.ypms.common.ContextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hero on 2018/3/12.
 */

public class ScreenPopupWindow extends PopupWindow {

    private LayoutInflater mInflater;

    private Context mContext;
    /**
     * 一级scroll数据
     */
    private List<Area> mDatas = new ArrayList<>();
    /**
     * 二级list数据
     */
    private List<Area> locations = new ArrayList<>();
    /**
     * pop整体View
     */
    private View popupView;
    /**
     *  二级list
     */
    private ListView lv;

    /**
     * 通过该接口与数据接口解耦，该PopupWindow不参与数据请求部分
     */
    private selectItemListener selectItemListener;
    private LinearLayout llAdd;

    private List<View> scrollViews = new ArrayList<>();

    private PopAreaAdapter popAreaAdapter;

    public void setSelectItemListener(selectItemListener selectItemListener){
        this.selectItemListener = selectItemListener;
    }

    public void setListData(List<Area> areas){
        popAreaAdapter.removeAllItems();
        popAreaAdapter.setItems(areas);
        popAreaAdapter.notifyDataSetChanged();
    }

    public ScreenPopupWindow(Context context, List<Area> datas,List<Area> locations){
        this.mContext = context;
        this.mDatas = datas;
        this.locations = locations;
        this.mInflater = LayoutInflater.from(mContext);
        popupView = mInflater.inflate(R.layout.layout_scree_popup, null);
        llAdd = popupView.findViewById(R.id.ll_add);
        lv = popupView.findViewById(R.id.lv);
        setContentView(popupView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x00ffffff);// 背景颜色全透明
        setBackgroundDrawable(cd);
        setAnimationStyle(R.style.popup_anim_style);
        setOutsideTouchable(true);
        update();
        initData();
    }

    private void initData() {
        for (int index=0; index<mDatas.size();index++){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout_text,null);
            TextView tvText = itemView.findViewById(R.id.tv_name);
            View view = itemView.findViewById(R.id.view_line);
            tvText.setText(mDatas.get(index).getName());
            if (index==0){
                tvText.setTextColor(ContextUtils.getColor(mContext,R.color.colorPrimary));
                tvText.setBackgroundColor(ContextUtils.getColor(mContext,R.color.white));
                view.setVisibility(View.VISIBLE);
            }
            itemView.setTag(index);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickView((Integer) v.getTag());
                }
            });
            scrollViews.add(itemView);
            llAdd.addView(itemView);
        }
        popAreaAdapter = new PopAreaAdapter(mContext);
        popAreaAdapter.setItems(locations);
        locations.get(0).setSelect(true);
        lv.setAdapter(popAreaAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Area area;
                for (int i = 0;i<popAreaAdapter.getCount();i++){
                    area = (Area) popAreaAdapter.getItem(i);
                    area.setSelect(false);
                }
                area = (Area) popAreaAdapter.getItem(position);
                area.setSelect(true);
                popAreaAdapter.notifyDataSetChanged();
                selectItemListener.onSelect(area);
            }
        });

    }

    private void clickView(int position) {
        for (int i=0;i<scrollViews.size();i++){
            View v = llAdd.getChildAt(i);
            TextView tvName =v.findViewById(R.id.tv_name);
            View view = v.findViewById(R.id.view_line);
            tvName.setBackgroundColor(i==position?ContextUtils.getColor(mContext,R.color.white)
                    :ContextUtils.getColor(mContext,R.color.bg_default));
            view.setVisibility(i==position?View.VISIBLE:View.GONE);
            tvName.setTextColor(i==position?ContextUtils.getColor(mContext,R.color.colorPrimary)
                    :ContextUtils.getColor(mContext,R.color.text_search_grey));
        }
        selectItemListener.onLevelSelect(mDatas.get(position));

    }


    public interface selectItemListener{
        void onSelect(Area area);
        void onLevelSelect(Area area);
    }

}
