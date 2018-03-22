package com.ypms.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ypms.common.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hero on 2018/3/1.
 */

public class RetrofitClient {

    private Retrofit retrofit;

    private RetrofitClient(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                .create();
        /**
         * 设置log级别
         *     NONE：不记录
         *     BASIC:请求/响应行
         *     HEADERS：请求/响应行 + 头
         *     BODY 请求/响应行 + 头 + 体
         */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        int timeOut = 60;

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(timeOut,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(timeOut,TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(timeOut, TimeUnit.SECONDS)//设置连接超时时间
                .retryOnConnectionFailure(true).build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Config.API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }


    private static class SingletonHolder{
        private static RetrofitClient retrofitClient = null;

        private SingletonHolder(){}
        private static RetrofitClient getInstance(){
            if (retrofitClient==null){
                synchronized (RetrofitClient.class){
                    if (retrofitClient==null){
                        retrofitClient = new RetrofitClient();
                    }
                }
            }
            return retrofitClient;
        }

    }
    /**
     * 获取RetrofitClient
     * @return
     */
    public static RetrofitClient getInstance(){
        return SingletonHolder.getInstance();
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

}
