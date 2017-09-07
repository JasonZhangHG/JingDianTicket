package com.education.myoschinatest.ui.Home3;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

public class BuyTicketInfoActivity extends AppCompatActivity {

    @BindView(R.id.rvBuyTicketInfoActivity)
    RecyclerView rvBuyTicketInfoActivity;

    private BuyTicketInfoActivityAdapter buyTicketInfoActivityAdapter;
    private List<DBBuyTicketBean> dbBuyTicketBeanArrayList = new ArrayList<>();
    private String userName;
    String[] mTitlesLast;
    int[] mIconUnselectIdsLast;
    private Subscription rxSubscription;
    private String[] mTitles = {"白帝城景点", "大足石刻", "水龙峡地缝", "武隆天生三桥", "仙女山国家深林公园"};
    private int[] mIconUnselectIds = {R.drawable.bai_di_cheng, R.drawable.da_zhu_shi_ke, R.drawable.sui_long_xia, R.drawable.wu_long, R.drawable.xian_nv_shan};
    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
    private DBUserInfoBean dbUserInfoBean;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket_info);
        ButterKnife.bind(this);

        state = getIntent().getIntExtra("22",0);
        if (state==1){
            SharedPreferences pref = getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
            userName = pref.getString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME, "");
        }else if (state == 2){
            userName = getIntent().getStringExtra("userName2");
        }

        Log.i("aaa", "当前用户名为：  " + userName);

        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
        if (dbUserInfoBeanList != null && dbUserInfoBeanList.size() > 0) {
            dbUserInfoBean = dbUserInfoBeanList.get(0);
        }


        initView();

        refreshMediaUpdateEvent();
    }

    public void initView() {

        final LinearLayoutManager manager = new LinearLayoutManager(BuyTicketInfoActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBuyTicketInfoActivity.setLayoutManager(manager);


        dbBuyTicketBeanArrayList = DBBuyTicketBeanUtils.getInstance().queryDataDependUserName(userName);
        Log.i("aaa", "已买的门票个数为：  " + dbBuyTicketBeanArrayList.size());
        if (dbBuyTicketBeanArrayList != null) {
            mTitlesLast = new String[dbBuyTicketBeanArrayList.size()];
            mIconUnselectIdsLast = new int[dbBuyTicketBeanArrayList.size()];
            for (int i = 0; i < dbBuyTicketBeanArrayList.size(); i++) {
                mTitlesLast[i] = mTitles[dbBuyTicketBeanArrayList.get(i).getPosition()];
                mIconUnselectIdsLast[i] = mIconUnselectIds[dbBuyTicketBeanArrayList.get(i).getPosition()];
            }
        }

        buyTicketInfoActivityAdapter = new BuyTicketInfoActivityAdapter(mTitlesLast, mIconUnselectIdsLast);
        rvBuyTicketInfoActivity.setAdapter(buyTicketInfoActivityAdapter);
        buyTicketInfoActivityAdapter.notifyDataSetChanged();
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(MediaUpdateEvent.class)
                .subscribe(new Action1<MediaUpdateEvent>() {
                    @Override
                    public void call(MediaUpdateEvent mediaUpdateEvent) {
                        if (ConstKey.BUY_DATA_SUCCESS.equals(mediaUpdateEvent.getMediaUpdateEvent())) {
                            dbBuyTicketBeanArrayList = DBBuyTicketBeanUtils.getInstance().queryDataDependUserName(userName);
                            Log.i("aaa", "已买的门票个数为：  " + dbBuyTicketBeanArrayList.size());
                            if (dbBuyTicketBeanArrayList != null) {
                                mTitlesLast = new String[dbBuyTicketBeanArrayList.size()];
                                mIconUnselectIdsLast = new int[dbBuyTicketBeanArrayList.size()];
                                for (int i = 0; i < dbBuyTicketBeanArrayList.size(); i++) {
                                    mTitlesLast[i] = mTitles[dbBuyTicketBeanArrayList.get(i).getPosition()];
                                    mIconUnselectIdsLast[i] = mIconUnselectIds[dbBuyTicketBeanArrayList.get(i).getPosition()];
                                }
                                buyTicketInfoActivityAdapter = new BuyTicketInfoActivityAdapter(mTitlesLast, mIconUnselectIdsLast);
                                rvBuyTicketInfoActivity.setAdapter(buyTicketInfoActivityAdapter);
                            } else {
                                mTitlesLast = new String[0];
                                mIconUnselectIdsLast = new int[0];
                            }
                            buyTicketInfoActivityAdapter.notifyDataSetChanged();
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


    class BuyTicketInfoActivityAdapter extends RecyclerView.Adapter<BuyTicketInfoActivityAdapter.BuyTicketInfoActivityViewHolder> {


        private String[] mTitlesLast;

        private int[] mIconUnselectIdsLast;

        public BuyTicketInfoActivityAdapter(String[] mTitlesLast, int[] mIconUnselectIdsLast) {

            this.mTitlesLast = mTitlesLast;
            this.mIconUnselectIdsLast = mIconUnselectIdsLast;
        }

        @Override
        public BuyTicketInfoActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(BuyTicketInfoActivity.this).inflate(R.layout.item_buy_ticket_info_activity, parent, false);

            BuyTicketInfoActivityViewHolder holder = new BuyTicketInfoActivityViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(BuyTicketInfoActivityViewHolder holder, final int position) {

            holder.tvItemBuyTicketInfoActivityTitle.setText(mTitlesLast[position]);
            Bitmap bitmap = BitmapFactory.decodeResource(BuyTicketInfoActivity.this.getResources(), mIconUnselectIdsLast[position]);
            holder.ivItemBuyTicketInfoActivityPicture.setImageBitmap(bitmap);
            SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String CreatedTime1 = sdr1.format(new Date(dbBuyTicketBeanArrayList.get(position).getCreatTimeAsId()));
            holder.tvItemBuyTicketInfoActivityTime.setText("购买时间为："+CreatedTime1 );
        }

        @Override
        public int getItemCount() {
            return mTitlesLast == null ? 0 : mTitlesLast.length;
        }

        class BuyTicketInfoActivityViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.ivItemBuyTicketInfoActivityPicture)
            ImageView ivItemBuyTicketInfoActivityPicture;
            @BindView(R.id.tvItemBuyTicketInfoActivityTitle)
            TextView tvItemBuyTicketInfoActivityTitle;
            @BindView(R.id.tvItemBuyTicketInfoActivityTime)
            TextView tvItemBuyTicketInfoActivityTime;

            public BuyTicketInfoActivityViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}

