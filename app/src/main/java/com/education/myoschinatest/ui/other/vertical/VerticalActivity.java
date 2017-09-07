package com.education.myoschinatest.ui.other.vertical;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.bean.RoomBean;
import com.education.myoschinatest.config.UrlConfig;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class VerticalActivity extends BaseActivity implements VerticalContract.View {
    @BindView(R.id.button_vertical)
    Button mButtonVertical;
    //此处使用了mvp设计模式,该activity只负责显示,不负责逻辑
    private VerticalContract.Presenter presenter;

    private boolean isNetworkBack;

    //初始化  view
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //view   findviewbyid
        // setonclick    setAdapter
        //view.set();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new VerticalPresenter(this);
        presenter.getVerticalFromDb();
        Map<String, String> params = new HashMap<String, String>();
        params.put(UrlConfig.Key.LIMIT, UrlConfig.DefaultValue.LIMIT);
        params.put(UrlConfig.Key.OFFSET, UrlConfig.DefaultValue.OFFSET);
        presenter.getVertical(params);
    }


//    @Override
//    protected void initView() {
//        presenter = new VerticalPresenter(this);
//        presenter.getVerticalFromDb();
//        Map<String, String> params = new HashMap<String, String>();
//        params.put(UrlConfig.Key.LIMIT, UrlConfig.DefaultValue.LIMIT);
//        params.put(UrlConfig.Key.OFFSET, UrlConfig.DefaultValue.OFFSET);
//        presenter.getVertical(params);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vertical;
    }

    @Override
    public void onGetVerticalSucess(List<RoomBean> rooms) {
        isNetworkBack=true;
        for (RoomBean room : rooms) {
            Logger.d(room.getRoomName());
        }
        //显示加在成功的效果
    }

    @Override
    public void onGetVerticalFail(String erro) {
        Logger.d(erro);
        //显示加载数据失败的效果
    }

    @Override
    public void onGetVerticalFromDb(List<RoomBean> rooms) {
       //显示数据库的内容
        if(!isNetworkBack) {
            for (RoomBean room : rooms) {
                Logger.d("数据库查询出的数据:" + room.getRoomName());
            }
        }
    }

    @OnClick(R.id.button_vertical)
    public void onClick() {
        //此处点击事件
        //在此处进行网络连接
        Map<String, String> params = new HashMap<String, String>();
        params.put(UrlConfig.Key.LIMIT, UrlConfig.DefaultValue.LIMIT);
        params.put(UrlConfig.Key.OFFSET, UrlConfig.DefaultValue.OFFSET);
        presenter.getVertical(params);
    }

    public void showLoading(){
        //显示加载进度
    }

    public void hintLoading(){
        //隐藏加载进度
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
