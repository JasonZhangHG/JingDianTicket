package com.education.myoschinatest.ui.other.login;

import com.education.myoschinatest.base.IBaseModle;
import com.education.myoschinatest.base.IBasePresenter;
import com.education.myoschinatest.base.IBaseView;
import com.education.myoschinatest.http.OnNetResult;

/**
 *  Created by Json on 2017/5/16.
 * 约束类
 */
public class LoginContract {

    public interface LoginModel extends IBaseModle{
         void login(String user,
                    String pasd,
                    OnNetResult<LoginBean> result);
//        void register();
    }

    public interface LoginView extends IBaseView{
        //如果要展示数据，加上参数
        //不展示数据，不加参数
        void showLoginSuccess(LoginBean data);
        void showFaile();
//        void showResisterSuccess();
    }

    public interface LoginPresenter extends IBasePresenter {
        void login(String user, String pasd);
//        void register();
    }

}
