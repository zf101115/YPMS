package com.ypms.net;

import android.net.ParseException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Hero on 2018/3/1.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private Disposable disposable;


    @Override
    public void onComplete() {
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onError(Throwable e) {
        RestBase restBase;
        if (e instanceof HttpException)
            restBase = handle(e);
        else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException)
            restBase = new RestBase(StatusCode.UNEXPECTED_ERROR,StatusCode.JSON_ERROR_TIPS);
        else if (e.getCause().equals(SocketTimeoutException.class)){
            restBase = new RestBase(StatusCode.TIME_OUT_ERROR, StatusCode.SOCKET_TIME_OUT);
        }
        else
            restBase = new RestBase(StatusCode.NETWORK_ERROR, StatusCode.UNEXPECTED_ERROR_TIPS);
        onError(restBase);
    }
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    public abstract void onError(RestBase resetBody);

    public static RestBase handle(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            if (error != null) {
                if (error.code()==400||error.code()==409){
                    Gson gson= new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    Type type = new TypeToken<RestBase>() {}.getType();
                    String errStrs ;
                    try {
                        errStrs = error.response().errorBody().string();
                        return gson.fromJson(errStrs, type);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (error.code() == 401 || error.code() == 403) {
                    return new RestBase(error.code(), StatusCode.AUTH_ERROR_TIPS);
                } else if (error.code() >= 400 && error.code() < 500) {
                    return new RestBase(error.code(),StatusCode.CLIENT_ERROR_TIPS);
                } else if (error.code() >= 500 && error.code() < 600) {
                    return new RestBase(error.code(),StatusCode.SERVER_ERROR_TIPS);
                }
            }
        } else {
            throwable.printStackTrace();
        }
        return null;
    }

}
