package com.ypms.home.model;

import com.ypms.common.BaseBean;

/**
 * Created by Hero on 2018/3/7.
 */

public class Mechanism extends BaseBean {
    private String title;
    private String pic;

    public Mechanism(String title){
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
