package com.education.myoschinatest.ui.other.home;

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

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private HomeContract.Modle modle;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        modle = new HomeModle();
    }

    @Override
    public void getVertical(Map<String, String> params) {
            modle.getVertical(params, new Callback<DataBean>() {
                @Override
                public void onResponse(Call<DataBean> call, Response<DataBean> response) {
                    DataBean dataBean = response.body();
                    final List<RoomBean> rooms = dataBean.getData();
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
}
