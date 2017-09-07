package com.education.myoschinatest.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;



public abstract class BaseFragment extends Fragment {

    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity= (Activity) context;
    }

    public Activity getMyActivity() {
        return activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            int show = savedInstanceState.getInt("isShow");
            if(show==1){
                //隐藏
            } //显示
        }

        View view=inflater.inflate(getLayoutId(),null);
        ButterKnife.bind(view);
        initView();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("isShow",isHidden()?1:0);
    }



    public abstract void   initView();
    public abstract  int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
