package com.education.myoschinatest;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBBuyTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBShouChangTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.litesuits.orm.LiteOrm;
import java.util.ArrayList;

public class MyApp extends Application {
    private static LiteOrm liteOrm;
    private static MyApp app;

    private ArrayList<Activity> activitys=new ArrayList<>();

    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initLiteOrm();
        DBUserInfoBeanUtils.Init(getApplicationContext());
        DBTicketBeanUtils.Init(getApplicationContext());
        DBBuyTicketBeanUtils.Init(getApplicationContext());
        DBShouChangTicketBeanUtils.Init(getApplicationContext());

        //用SharedPreferences 存储用户名和密码
        SharedPreferences pref = getApplicationContext().getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference,MODE_PRIVATE);
        editor = pref.edit();
    }

    private void initLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log
    }


    public void  addActivity(Activity activity){
        if(!activitys.contains(activity))
        activitys.add(activity);
    }

    public void removeActiivty(Activity activity){
        if(activitys.contains(activity)){
            activitys.remove(activity);
        }
    }

    public void exit(){
        for (Activity a:activitys) {
            a.finish();
        }
    }

    public static MyApp getInstance() {
        return app;
    }

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }
}
