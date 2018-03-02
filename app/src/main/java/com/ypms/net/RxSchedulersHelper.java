package com.ypms.net;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.ypms.common.BaseFragment;
import com.ypms.common.ToolBarActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hero on 2018/3/1.
 */

public class RxSchedulersHelper<T> {

    public ObservableTransformer<T, T> io_main(final ToolBarActivity rxActivity) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(rxActivity.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }

        };
    }

    public ObservableTransformer<T, T> io_main(final BaseFragment rxFragment) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(rxFragment.<T>bindUntilEvent(FragmentEvent.DESTROY));
            }

        };
    }
}
