package com.ypms.customWidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.ypms.R;
import com.ypms.area.adapter.PopAreaAdapter;
import com.ypms.area.model.Area;

import java.util.List;

/**
 * Created by Hero on 2018/4/4.
 */

public class SinglePopupWindow extends PopupWindow {
    private LayoutInflater mInflater;
    private Context mContext;
    private ListView lv;
    private List<Area> datas;
    private View popupView;
    private PopAreaAdapter popAreaAdapter;
    private selectSingleItemListener selectSingleItemListener;

    public void setSelectListener(selectSingleItemListener selectListener){
        this.selectSingleItemListener = selectListener;
    }

    public SinglePopupWindow (Context context, List<Area> list){
        this.mContext = context;
        this.datas = list;
        this.mInflater = LayoutInflater.from(mContext);
        popupView = mInflater.inflate(R.layout.layout_single_lv, null);
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
        popAreaAdapter = new PopAreaAdapter(mContext);
        popAreaAdapter.setItems(datas);
        lv.setAdapter(popAreaAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectSingleItemListener.onSelect(datas.get(position));
            }
        });
    }

    public interface selectSingleItemListener{
        void onSelect(Area area);
    }
}
