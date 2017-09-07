package com.education.myoschinatest.http;

import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.config.UrlConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Json on 2017/5/16.
 */

public interface IDouyu {
//    http://capi.douyucdn.cn/api/v1/getVerticalRoom
    @GET(UrlConfig.Path.URL_VERTICAL)
    Call<DataBean> getVerticalData(@QueryMap()Map<String,String> params);
}
