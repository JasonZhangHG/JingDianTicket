package com.education.myoschinatest.ui.other.vertical;

import android.os.Handler;
import android.os.Looper;

import com.education.myoschinatest.MyApp;
import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.bean.RoomBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Created by Json on 2017/5/16.
 */

public class VerticalPresenter implements VerticalContract.Presenter {
    private VerticalContract.View view;
    private VerticalContract.Modle modle;

    public VerticalPresenter(VerticalContract.View view) {
        this.view = view;
        this.modle = new VerticalModle();
    }
    //登陆，退出，支付，下订单，购物车

    @Override
    public void getVertical(Map<String, String> params) {
        //不进行网络连接,只负责逻辑,即调用对应的modle的方法
        modle.getVertical(params, new Callback<DataBean>() {
            @Override
            public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                DataBean dataBean = response.body();
                final List<RoomBean> rooms = dataBean.getData();
                //此处存数据库
                MyApp.getLiteOrm().insert(rooms);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onGetVerticalSucess(rooms);
                    }
                });
            }

            @Override
            public void onFailure(Call<DataBean> call, Throwable t) {
                t.printStackTrace();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onGetVerticalFail("网络连接失败");
                    }
                });
            }
        });
    }

    @Override
    public void getVerticalFromDb() {
            modle.getVerticalFromDb(new VerticalModle.FromDbListener() {
                @Override
                public void onSucess(List<RoomBean> rooms) {
                    view.onGetVerticalFromDb(rooms);
                }

                @Override
                public void onFail(String msg) {
                    view.onGetVerticalFail("错误");
                }
            });
    }
}
