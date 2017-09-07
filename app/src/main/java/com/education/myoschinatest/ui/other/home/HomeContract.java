package com.education.myoschinatest.ui.other.home;

import com.education.myoschinatest.base.IBaseModle;
import com.education.myoschinatest.base.IBasePresenter;
import com.education.myoschinatest.base.IBaseView;
import com.education.myoschinatest.bean.DataBean;
import com.education.myoschinatest.bean.RoomBean;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;

/**
 *  Created by Json on 2017/5/16.
 */

public class HomeContract {
    public interface View extends IBaseView{
        public void onGetVerticalSucess(List<RoomBean> rooms);
        public void onGetVerticalFail(String erro);
    }
    public interface Modle extends IBaseModle {
        public void getVertical(Map<String,String> params, Callback<DataBean> callback);
    }
    public interface  Presenter extends IBasePresenter {
        public void getVertical(Map<String,String> params);
    }
}
