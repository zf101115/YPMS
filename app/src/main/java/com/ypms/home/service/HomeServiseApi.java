package com.ypms.home.service;

import com.ypms.home.model.Mechanism;
import com.ypms.home.model.MechanismPage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hero on 2018/3/19.
 */

public interface HomeServiseApi {
    @GET("mock/9/project/api/list")
    Observable<MechanismPage> getHomeList(@Query("page") Integer page);

    @GET("mock/9/project/api/")
    Observable<Mechanism> getInvestmentDetail();

}
