package com.education.myoschinatest.ui.Home3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBUserInfoBean;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.tvRegisterActivityTitle)
    TextView tvRegisterActivityTitle;
    @BindView(R.id.edtUserInfoActivityName)
    EditText edtUserInfoActivityName;
    @BindView(R.id.edtUserInfoActivityOld)
    EditText edtUserInfoActivityOld;
    @BindView(R.id.edtUserInfoActivityTel)
    EditText edtUserInfoActivityTel;
    @BindView(R.id.edtUserInfoActivityMail)
    EditText edtUserInfoActivityMail;
    @BindView(R.id.btnUserInfoActivitySubmit)
    Button btnUserInfoActivitySubmit;
    private int state;

    private String userName;
    private DBUserInfoBean dbUserInfoBean;
    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);


        state = getIntent().getIntExtra("11",0);

        if (state ==1 ){
            SharedPreferences pref = UserInfoActivity.this.getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
            userName = pref.getString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME, "");
        }else if (state ==2){
            userName = getIntent().getStringExtra("userName");
        }

        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
        if ((dbUserInfoBeanList!=null)&&(dbUserInfoBeanList.size() > 0)){
           dbUserInfoBean = dbUserInfoBeanList.get(0);
            edtUserInfoActivityName.setText(dbUserInfoBean.getName());
            edtUserInfoActivityOld.setText(dbUserInfoBean.getOld());
            edtUserInfoActivityTel.setText(dbUserInfoBean.getTellPhone());
            edtUserInfoActivityMail.setText(dbUserInfoBean.getMail());
        }



        Log.i("aaa", "当前用户名为：  " + userName);
        btnUserInfoActivitySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSumitClick();
            }
        });
    }

    public void setSumitClick(){

        String name =  edtUserInfoActivityName.getText().toString();
        String old = edtUserInfoActivityOld.getText().toString();
        String tellPhone = edtUserInfoActivityTel.getText().toString();
        String mail = edtUserInfoActivityMail.getText().toString();

        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(old)||TextUtils.isEmpty(tellPhone)||TextUtils.isEmpty(mail)){
            Toast.makeText(UserInfoActivity.this,"请输入完整信息后再点击修改",Toast.LENGTH_SHORT).show();
        }else {
            dbUserInfoBean.name = name;
            dbUserInfoBean.old = old;
            dbUserInfoBean.tellPhone = tellPhone;
            dbUserInfoBean.mail = mail;
            DBUserInfoBeanUtils.getInstance().updateData(dbUserInfoBean);
            Toast.makeText(UserInfoActivity.this, "修改个人信息成功", Toast.LENGTH_SHORT).show();
        }
    }
}


