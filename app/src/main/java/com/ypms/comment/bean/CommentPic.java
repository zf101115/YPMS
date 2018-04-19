package com.ypms.comment.bean;

import com.ypms.common.BaseBean;

/**
 * Created by Hero on 2018/4/18.
 */

public class CommentPic extends BaseBean{
    public CommentPic(String path){
        this.path = path;
    }

    private Integer id;
    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
