package com.ypms.home.Controll;

import com.ypms.common.BaseFragment;
import com.ypms.common.ToolBarActivity;
import com.ypms.home.model.Mechanism;
import com.ypms.home.model.MechanismPage;
import com.ypms.home.service.HomeServiseApi;
import com.ypms.net.BaseObserver;
import com.ypms.net.RestBase;
import com.ypms.net.RetrofitClient;
import com.ypms.net.RxSchedulersHelper;

/**
 * Created by Hero on 2018/3/19.
 */

public class HomeController {

    public void getHomeData(BaseFragment mFragment, final HomeDataListener homeDataListener){
        RetrofitClient.getInstance().create(HomeServiseApi.class).getInvestmentDetail()
                .compose(new RxSchedulersHelper<Mechanism>().io_main(mFragment))
                .subscribe(new BaseObserver<Mechanism>() {
                    @Override
                    public void onSuccess(Mechanism mechanismPage) {
                        homeDataListener.onComplete(mechanismPage);
                    }

                    @Override
                    public void onError(RestBase resetBody) {
                        homeDataListener.onFailed(resetBody);
                    }
                });
    }


    public interface HomeDataListener{
        void onComplete(Mechanism mechanismPage);
        void onFailed(RestBase resetBody);
    }



}
