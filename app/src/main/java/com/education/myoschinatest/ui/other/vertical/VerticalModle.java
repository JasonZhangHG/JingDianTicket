package com.education.myoschinatest.ui.other.vertical;

import com.education.myoschinatest.MyApp;
import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.bean.RoomBean;
import com.education.myoschinatest.http.HttpUtils;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;

/**
 *  Created by Json on 2017/5/16.
 */

public class VerticalModle implements VerticalContract.Modle {
    @Override
    public void getVertical(Map<String, String> params, Callback<DataBean> callback) {
        HttpUtils.getInstance().getVerticalData(params, callback);
    }

    //回调  Handler  EventBus   LcalBroadcaset
    public interface FromDbListener {
        public void onSucess(List<RoomBean> rooms);

        public void onFail(String msg);
    }

    @Override
    public void getVerticalFromDb(FromDbListener listener) {
        List<RoomBean> rooms = MyApp.getLiteOrm().query(RoomBean.class);
        if (listener != null) {
            listener.onSucess(rooms);
        }
    }
}
