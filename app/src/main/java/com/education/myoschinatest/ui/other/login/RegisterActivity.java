package com.education.myoschinatest.ui.other.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBUserInfoBean;

import org.greenrobot.greendao.annotation.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tvRegisterActivityTitle)
    TextView tvRegisterActivityTitle;
    @BindView(R.id.edtRegisterActivityUserName)
    EditText edtRegisterActivityUserName;
    @BindView(R.id.edtRegisterActivityPassWord1)
    EditText edtRegisterActivityPassWord1;
    @BindView(R.id.edtRegisterActivityPassWord2)
    EditText edtRegisterActivityPassWord2;
    @BindView(R.id.edtRegisterActivityName)
    EditText edtRegisterActivityName;
    @BindView(R.id.edtRegisterActivityOld)
    EditText edtRegisterActivityOld;
    @BindView(R.id.edtRegisterActivityTel)
    EditText edtRegisterActivityTel;
    @BindView(R.id.edtRegisterActivityMail)
    EditText edtRegisterActivityMail;
    @BindView(R.id.btnRegisterActivitySubmit)
    Button btnRegisterActivitySubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiest);
        ButterKnife.bind(this);
        btnRegisterActivitySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegisterClick();
            }
        });
    }

    public void setRegisterClick(){
        String userName = edtRegisterActivityUserName.getText().toString();
        String passWord1 = edtRegisterActivityPassWord1.getText().toString();
         String passWord2 = edtRegisterActivityPassWord2.getText().toString();
         String name = edtRegisterActivityName.getText().toString();
         String old = edtRegisterActivityOld.getText().toString();
         String tellPhone = edtRegisterActivityTel.getText().toString();
         String mail = edtRegisterActivityMail.getText().toString();

        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord1)||TextUtils.isEmpty(passWord2)||TextUtils.isEmpty(name)||TextUtils.isEmpty(old)||TextUtils.isEmpty(tellPhone)||TextUtils.isEmpty(mail)){
            Toast.makeText(RegisterActivity.this,"请输入完整信息后再注册",Toast.LENGTH_SHORT).show();
        }else {
            List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
            dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
            if ((dbUserInfoBeanList!=null)&&(dbUserInfoBeanList.size() == 0)) {
                if (passWord1.equals(passWord2)) {
                    DBUserInfoBean dbUserInfoBean = new DBUserInfoBean();
                    dbUserInfoBean.setCreatTimeAsId(getTime());
                    dbUserInfoBean.setUserName(userName);
                    dbUserInfoBean.setPassWord(passWord1);
                    dbUserInfoBean.setName(name);
                    dbUserInfoBean.setOld(old);
                    dbUserInfoBean.setTellPhone(tellPhone);
                    dbUserInfoBean.setMail(mail);
                    DBUserInfoBeanUtils.getInstance().insertOneData(dbUserInfoBean);
                    Toast.makeText(RegisterActivity.this, "恭喜您，注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(RegisterActivity.this, "此用户名已注册，请更换其他用户名注册", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public long getTime() {
        return System.currentTimeMillis();//获取系统时间戳
    }

}
