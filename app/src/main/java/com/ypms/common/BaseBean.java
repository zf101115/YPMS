package com.ypms.common;

import java.io.Serializable;

/**
 * Created by Hero on 2018/3/7.
 */

public class BaseBean implements Serializable {
    private Integer status;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
