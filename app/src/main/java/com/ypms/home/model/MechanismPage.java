package com.ypms.home.model;

import com.google.gson.annotations.SerializedName;
import com.ypms.common.BaseBean;

import java.util.List;

/**
 * Created by Hero on 2018/3/19.
 */

public class MechanismPage extends BaseBean {

    public Integer count;

    public String previous;

    public String next;

    @SerializedName("results")
    private List<Mechanism> mechanisms;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Mechanism> getMechanisms() {
        return mechanisms;
    }

    public void setMechanisms(List<Mechanism> mechanisms) {
        this.mechanisms = mechanisms;
    }
}
