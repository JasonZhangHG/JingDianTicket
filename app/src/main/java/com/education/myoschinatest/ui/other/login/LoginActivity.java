package com.education.myoschinatest.ui.other.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.MyApp;
import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBTicketBean;
import com.education.myoschinatest.bean.DBUserInfoBean;
import com.education.myoschinatest.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登录
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editLoginActivityUsrName)
    TextInputEditText editLoginActivityUsrName;
    @BindView(R.id.editLoginActivityPassWord)
    TextInputEditText editLoginActivityPassWord;
    @BindView(R.id.btnLoginActivityLogin)
    AppCompatButton btnLoginActivityLogin;
    @BindView(R.id.tvLoginActivityRegister)
    TextView tvLoginActivityRegister;
    @BindView(R.id.llLoginActivityAll)
    LinearLayout llLoginActivityAll;

    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //初始化管理员
        List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName("admin");
        if ( dbUserInfoBeanList!=null&& dbUserInfoBeanList.size() == 0){
            DBUserInfoBean dbUserInfoBean = new DBUserInfoBean();
            dbUserInfoBean.setCreatTimeAsId(getTime());
            dbUserInfoBean.setUserName("admin");
            dbUserInfoBean.setPassWord("admin");
            dbUserInfoBean.setName("管理员");
            dbUserInfoBean.setOld("25");
            dbUserInfoBean.setTellPhone("110");
            dbUserInfoBean.setMail("123456@qq.com");
            DBUserInfoBeanUtils.getInstance().insertOneData(dbUserInfoBean);
        }


       //初始化景点门票
        List<DBTicketBean> dbDBTicketBeanList = new ArrayList<>();
        dbDBTicketBeanList =  DBTicketBeanUtils.getInstance().queryData();
        if (dbDBTicketBeanList!=null&&dbDBTicketBeanList.size()==0){
            //景点为 作为ID 白帝城景点 1, "大足石刻" 2, "水龙峡地缝" 3, "武隆天生三桥" 4, "仙女山国家深林公园 5
            DBTicketBean dbTicketBean1 = new  DBTicketBean();
            dbTicketBean1.setJingDianID(1);
            dbTicketBean1.setJingDianName("白帝城景点");
            dbTicketBean1.setJingDianYuPiao(100);
            DBTicketBeanUtils.getInstance().insertOneData(dbTicketBean1);

            DBTicketBean dbTicketBean2 = new  DBTicketBean();
            dbTicketBean2.setJingDianID(2);
            dbTicketBean2.setJingDianName("大足石刻");
            dbTicketBean2.setJingDianYuPiao(100);
            DBTicketBeanUtils.getInstance().insertOneData(dbTicketBean2);

            DBTicketBean dbTicketBean3 = new  DBTicketBean();
            dbTicketBean3.setJingDianID(3);
            dbTicketBean3.setJingDianName("水龙峡地缝");
            dbTicketBean3.setJingDianYuPiao(100);
            DBTicketBeanUtils.getInstance().insertOneData(dbTicketBean3);

            DBTicketBean dbTicketBean4 = new  DBTicketBean();
            dbTicketBean4.setJingDianID(4);
            dbTicketBean4.setJingDianName("武隆天生三桥");
            dbTicketBean4.setJingDianYuPiao(100);
            DBTicketBeanUtils.getInstance().insertOneData(dbTicketBean4);

            DBTicketBean dbTicketBean5 = new  DBTicketBean();
            dbTicketBean5.setJingDianID(5);
            dbTicketBean5.setJingDianName("仙女山国家深林公园");
            dbTicketBean5.setJingDianYuPiao(100);
            DBTicketBeanUtils.getInstance().insertOneData(dbTicketBean5);
        }

        editor = MyApp.editor;

        btnLoginActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginOnclick();
            }
        });

        tvLoginActivityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setLoginOnclick(){
        String userName = editLoginActivityUsrName.getText().toString();
        String pwd = editLoginActivityPassWord.getText().toString();
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(pwd)){
            Toast.makeText(LoginActivity.this,"请输入用户名或密码",Toast.LENGTH_SHORT).show();
        }else {
            dbUserInfoBeanList =  DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
            if (dbUserInfoBeanList.size() ==0){
                Toast.makeText(LoginActivity.this,"该用户名未注册",Toast.LENGTH_SHORT).show();
            }else {
                 if (pwd.equals(dbUserInfoBeanList.get(0).getPassWord())){
                     Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                     startActivity(intent);
                     //保存用户名
                     editor.putString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME,userName);
                     editor.commit();
                     Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                 }else {
                     Toast.makeText(LoginActivity.this,"密码输入错误",Toast.LENGTH_SHORT).show();
                 }
            }
        }
    }

    public long getTime() {
        return System.currentTimeMillis();//获取系统时间戳
    }
}
