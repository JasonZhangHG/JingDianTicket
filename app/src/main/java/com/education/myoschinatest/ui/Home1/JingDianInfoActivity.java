package com.education.myoschinatest.ui.Home1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBBuyTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBShouChangTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.DBBeanUtils.RxBus;
import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBBuyTicketBean;
import com.education.myoschinatest.bean.DBShouChangTicketBean;
import com.education.myoschinatest.bean.DBTicketBean;
import com.education.myoschinatest.bean.DBUserInfoBean;
import com.education.myoschinatest.bean.MediaUpdateEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;


public class JingDianInfoActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tvJingDianInfoActivityTitle)
    TextView tvJingDianInfoActivityTitle;
    @BindView(R.id.ivJingDianInfoActivityPicture)
    ImageView ivJingDianInfoActivityPicture;
    @BindView(R.id.btnJingDianInfoActivityBuyTic)
    Button btnJingDianInfoActivityBuyTic;
    @BindView(R.id.btnJingDianInfoActivityShouChangTic)
    Button btnJingDianInfoActivityShouChangTic;
    @BindView(R.id.btnJingDianInfoActivityInfo)
    Button btnJingDianInfoActivityInfo;
    @BindView(R.id.tvJingDianInfoActivityYuPiao)
    TextView tvJingDianInfoActivityYuPiao;
    @BindView(R.id.tvJingDianInfoActivityPrice)
    TextView tvJingDianInfoActivityPrice;


    private String[] mTitles = {"白帝城景点", "大足石刻", "水龙峡地缝", "武隆天生三桥", "仙女山国家深林公园"};
    private int[] mIconUnselectIds = {R.drawable.bai_di_cheng, R.drawable.da_zhu_shi_ke, R.drawable.sui_long_xia, R.drawable.wu_long, R.drawable.xian_nv_shan};
    private String[] prices = {"门面价：¥120  团购价：¥100", "门面价：¥90  团购价：¥75", "门面价：¥115  团购价：¥100", "门面价：¥20  团购价：¥10", "门面价：¥20  团购价：¥10"};
    private int position;
    private String userName;
    private DBUserInfoBean dbUserInfoBean;
    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();

    private int yuPiao;
    private Subscription rxSubscription;
    private List<DBTicketBean> dbTicketBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_dian_info);
        ButterKnife.bind(this);
        position = getIntent().getIntExtra("JingDianPosition", 0);
        btnJingDianInfoActivityBuyTic.setOnClickListener(this);
        btnJingDianInfoActivityShouChangTic.setOnClickListener(this);
        btnJingDianInfoActivityInfo.setOnClickListener(this);
        tvJingDianInfoActivityTitle.setText(mTitles[position]);
        Bitmap bitmap = BitmapFactory.decodeResource(JingDianInfoActivity.this.getResources(), mIconUnselectIds[position]);
        ivJingDianInfoActivityPicture.setImageBitmap(bitmap);
        tvJingDianInfoActivityPrice.setText("门票价格："+prices[position]);

        dbTicketBeanList = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitles[position]);

        tvJingDianInfoActivityYuPiao.setText("余票数：" + dbTicketBeanList.get(0).getJingDianYuPiao());

        SharedPreferences pref = JingDianInfoActivity.this.getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
        userName = pref.getString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME, "");

        Log.i("aaa", "当前用户名为：  " + userName);

        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
        if (dbUserInfoBeanList != null && dbUserInfoBeanList.size() > 0) {
            dbUserInfoBean = dbUserInfoBeanList.get(0);
        }

        refreshMediaUpdateEvent();
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(MediaUpdateEvent.class)
                .subscribe(new Action1<MediaUpdateEvent>() {
                    @Override
                    public void call(MediaUpdateEvent mediaUpdateEvent) {
                        if (ConstKey.BUY_DATA_SUCCESS.equals(mediaUpdateEvent.getMediaUpdateEvent())) {
                            dbTicketBeanList = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitles[position]);
                            tvJingDianInfoActivityYuPiao.setText("余票数：" + dbTicketBeanList.get(0).getJingDianYuPiao());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnJingDianInfoActivityBuyTic:
                dbTicketBeanList = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitles[position]);
                if (dbTicketBeanList.get(0).getJingDianYuPiao()>0) {
                    Log.i("aaa", "点击购买时的当前用户信息为： " + dbUserInfoBean.toString());
                    DBBuyTicketBean dbBuyTicketBean = new DBBuyTicketBean();
                    dbBuyTicketBean.setCreatTimeAsId(getTime());
                    dbBuyTicketBean.setUserName(userName);
                    dbBuyTicketBean.setBuyerName(dbUserInfoBean.getName());
                    dbBuyTicketBean.setBuyerOld(dbUserInfoBean.getOld());
                    dbBuyTicketBean.setBuyerTel(dbUserInfoBean.getTellPhone());
                    dbBuyTicketBean.setBuyerMail(dbUserInfoBean.getMail());
                    dbBuyTicketBean.setJingdainMing(mTitles[position]);
                    dbBuyTicketBean.setPosition(position);
                    DBBuyTicketBeanUtils.getInstance().insertOneData(dbBuyTicketBean);

                    //更新余票 start
                    DBTicketBean dbTicketBean = dbTicketBeanList.get(0);
                    dbTicketBean.jingDianYuPiao = dbTicketBean.getJingDianYuPiao() - 1;
                    DBTicketBeanUtils.getInstance().updateData(dbTicketBean);
                    //更新余票 end

                    RxBus.getDefault().post(new MediaUpdateEvent(ConstKey.BUY_DATA_SUCCESS));
                    Toast.makeText(JingDianInfoActivity.this, "恭喜您购票成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(JingDianInfoActivity.this, "余票不足，无法购买", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnJingDianInfoActivityShouChangTic:
                dbTicketBeanList = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitles[position]);
                if (dbTicketBeanList.get(0).getJingDianYuPiao()>0) {
                    Log.i("aaa", "点击添加购物车时的当前用户信息为： " + dbUserInfoBean.toString());
                    DBShouChangTicketBean dbShouChangTicketBean = new DBShouChangTicketBean();
                    dbShouChangTicketBean.setCreatTimeAsId(getTime());
                    dbShouChangTicketBean.setUserName(userName);
                    dbShouChangTicketBean.setBuyerName(dbUserInfoBean.getName());
                    dbShouChangTicketBean.setBuyerOld(dbUserInfoBean.getOld());
                    dbShouChangTicketBean.setBuyerTel(dbUserInfoBean.getTellPhone());
                    dbShouChangTicketBean.setBuyerMail(dbUserInfoBean.getMail());
                    dbShouChangTicketBean.setJingdainMing(mTitles[position]);
                    dbShouChangTicketBean.setPosition(position);
                    DBShouChangTicketBeanUtils.getInstance().insertOneData(dbShouChangTicketBean);
                    RxBus.getDefault().post(new MediaUpdateEvent(ConstKey.BUY_DATA_SUCCESS));
                    Toast.makeText(JingDianInfoActivity.this, "恭喜您添加购物车成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(JingDianInfoActivity.this, "余票不足，无法收藏", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnJingDianInfoActivityInfo:
                Intent intent = new Intent(JingDianInfoActivity.this, JingDianJieshaoActivity.class);
                intent.putExtra("JingDianURL", position);
                startActivity(intent);
                break;
        }
    }

    public long getTime() {
        return System.currentTimeMillis();//获取系统时间戳
    }
}
