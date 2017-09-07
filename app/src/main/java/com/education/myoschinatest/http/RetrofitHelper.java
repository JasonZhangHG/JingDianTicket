package com.education.myoschinatest.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper instance;

    private IApi api;

    public static final String BASE_URL="";

    private RetrofitHelper() {
        //初始化Retrofit
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client())
                .build();
         api = retrofit.create(IApi.class);
    }

    public IApi getApi() {
        return api;
    }

    private OkHttpClient client() {
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
//                .addNetworkInterceptor() Log拦截器
                .build();
        return client;
    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }
}