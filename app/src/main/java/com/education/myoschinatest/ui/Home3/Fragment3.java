package com.education.myoschinatest.ui.Home3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class Fragment3 extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.btnFragment3UserInfo)
    Button btnFragment3UserInfo;
    @BindView(R.id.btnFragment3BuyTicket)
    Button btnFragment3BuyTicket;
    @BindView(R.id.btnFragment3Manage)
    Button btnFragment3Manage;

    private String userName;


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment3;
    }

    public static Fragment3 instanceFragment() {

        Fragment3 fragment3 = new Fragment3();
        return fragment3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences pref = getActivity().getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
        userName = pref.getString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME, "");

        Log.i("aaa", "当前用户名为：  " + userName);

        //判断当前用户是否为： admin
        if ("admin".equals(userName)){
            btnFragment3Manage.setVisibility(View.VISIBLE);
        }else {
            btnFragment3Manage.setVisibility(View.GONE);
        }
        btnFragment3UserInfo.setOnClickListener(this);
        btnFragment3BuyTicket.setOnClickListener(this);
        btnFragment3Manage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFragment3UserInfo:
                Intent intent = new Intent(getActivity(),UserInfoActivity.class);
                intent.putExtra("11",1);
                startActivity(intent);
                break;
            case R.id.btnFragment3BuyTicket:
                Intent intent2 = new Intent(getActivity(),BuyTicketInfoActivity.class);
                intent2.putExtra("22",1);
                startActivity(intent2);
                break;
            case R.id.btnFragment3Manage:
                Intent intent3 = new Intent(getActivity(),AllUserActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
