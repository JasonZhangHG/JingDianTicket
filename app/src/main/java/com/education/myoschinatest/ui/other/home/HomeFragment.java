package com.education.myoschinatest.ui.other.home;

import android.widget.Button;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;
import com.education.myoschinatest.bean.RoomBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  Created by Json on 2017/5/16.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.button_vertical)
    Button buttonVertical;
    private HomeContract.Presenter presenter;


    @Override
    public void initView() {
        presenter = new HomePresenter(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onGetVerticalSucess(List<RoomBean> rooms) {
        for (RoomBean room : rooms) {
            Logger.d(room.getRoomName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onGetVerticalFail(String erro) {
        Logger.d(erro);
    }


    @OnClick(R.id.button_vertical)
    public void onClick() {
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
