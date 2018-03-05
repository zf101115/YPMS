package com.ypms.common;

/**
 * Created by Hero on 2018/3/5.
 * 懒加载基类，确保fragment可见时加载数据
 */

public abstract class LazyBaseFragment extends BaseFragment {

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    protected abstract void lazyLoad();

}
