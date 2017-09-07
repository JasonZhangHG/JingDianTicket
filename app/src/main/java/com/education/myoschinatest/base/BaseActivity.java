package com.education.myoschinatest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.education.myoschinatest.MyApp;

import butterknife.ButterKnife;



public abstract  class BaseActivity extends AppCompatActivity {
    MyApp app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
         app = (MyApp) getApplication();
        app.addActivity(this);
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //做数据统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        //做数据统计
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.removeActiivty(this);
    }

    protected abstract void initView(Bundle savedInstanceState);

    public abstract int getLayoutId();
}
