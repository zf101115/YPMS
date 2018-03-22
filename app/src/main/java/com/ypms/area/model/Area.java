package com.ypms.area.model;

import com.ypms.common.BaseBean;

/**
 * Created by Hero on 2018/3/13.
 */

public class Area extends BaseBean {


    public Area (String name,Integer id){
        this.name = name;
        this.id = id;
    }
    private Integer id;
    private String name;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
