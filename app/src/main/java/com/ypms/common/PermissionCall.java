package com.ypms.common;

import java.util.List;

/**
 * Created by Hero on 2018/3/22.
 */

public interface PermissionCall {
    void success(List<Integer> list);
    void fail(List<Integer> list);
}
