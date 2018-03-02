package com.ypms.net;

import java.io.Serializable;

/**
 * Created by Hero on 2018/3/1.
 */

public class RestBase implements Serializable {
    protected Integer status;
    protected String detail;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public RestBase(Integer status, String detail) {
        this.detail = detail;
        this.status = status;
    }



}
