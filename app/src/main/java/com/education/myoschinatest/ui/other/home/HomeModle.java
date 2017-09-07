package com.education.myoschinatest.ui.other.home;

import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.http.HttpUtils;

import java.util.Map;

import retrofit2.Callback;

/**
 *  Created by Json on 2017/5/16.
 */

public class HomeModle implements HomeContract.Modle {
    @Override
    public void getVertical(Map<String, String> params, Callback<DataBean> callback) {
        HttpUtils.getInstance().getVerticalData(params, callback);
    }
}
