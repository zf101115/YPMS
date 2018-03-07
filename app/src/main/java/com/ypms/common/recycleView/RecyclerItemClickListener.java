package com.ypms.common.recycleView;

import android.view.View;

/**
 * Created by Hero on 2018/3/5.
 */

public interface RecyclerItemClickListener {
    void OnItemClick(View view,int position);
    void OnItemLongClick(View view,int position);
}
