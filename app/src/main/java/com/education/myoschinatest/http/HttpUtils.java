package com.education.myoschinatest.http;

import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.config.UrlConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    private static volatile HttpUtils singleton;
    private Retrofit retrofit;
    private IDouyu douyu;

    private HttpUtils() {
        retrofit = createRetrofit();
        douyu = createDouyu();
    }

    public static HttpUtils getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new HttpUtils();
                }
            }
        }
        return singleton;
    }

    /**
     * 创建retrofit的实例
     * @return
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.Path.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();
    }

    /**
     * 创建gson实例,用于retrofit解析返回的结果
     * @return
     */
    private Gson createGson() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        return gson;
    }

    /**
     * 创建网络连接接口的实例
     * @return
     */
    private IDouyu createDouyu() {
        IDouyu douyu = retrofit.create(IDouyu.class);
        return douyu;
    }

    /**
     * 网络获取颜值的方法
     *
     * @param params   参数列表 包含两个参数 limit和offset
     * @param callback 访问数据的回调接口
     */
    public void getVerticalData(Map<String, String> params, Callback<DataBean> callback) {

        Call<DataBean> call = douyu.getVerticalData(params);
        call.enqueue(callback);
        //存数据库
    }


}