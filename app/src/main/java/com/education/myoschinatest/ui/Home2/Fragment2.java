package com.education.myoschinatest.ui.Home2;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.education.myoschinatest.base.BaseFragment;
import com.education.myoschinatest.bean.DBBuyTicketBean;
import com.education.myoschinatest.bean.DBShouChangTicketBean;
import com.education.myoschinatest.bean.DBTicketBean;
import com.education.myoschinatest.bean.DBUserInfoBean;
import com.education.myoschinatest.bean.MediaUpdateEvent;
import com.education.myoschinatest.ui.Home1.JingDianInfoActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends BaseFragment {
    private RecyclerView rvFragment2;
    private Fragment2Adapter fragment2Adapter;
    private List<DBShouChangTicketBean> dbShouChangTicketBeanList = new ArrayList<>();
    private String userName;
    String[] mTitlesLast;
    int[] mIconUnselectIdsLast;
    private Subscription rxSubscription;
    private String[] mTitles = {"白帝城景点", "大足石刻", "水龙峡地缝", "武隆天生三桥", "仙女山国家深林公园"};
    private int[] mIconUnselectIds = {R.drawable.bai_di_cheng, R.drawable.da_zhu_shi_ke, R.drawable.sui_long_xia, R.drawable.wu_long, R.drawable.xian_nv_shan};
    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
    private DBUserInfoBean dbUserInfoBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment2;
    }

    public static Fragment2 instanceFragment() {

        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        rvFragment2 = (RecyclerView) view.findViewById(R.id.rvFragment2);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences pref = getActivity().getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
        userName = pref.getString(ConstKey.KEY_WIFI_REMIND_Shared_Preference_NAME, "");

        Log.i("aaa", "当前用户名为：  " + userName);

        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryDataDependUserName(userName);
        if ( dbUserInfoBeanList!=null&& dbUserInfoBeanList.size()>0){
            dbUserInfoBean = dbUserInfoBeanList.get(0);
        }


        initView();

        refreshMediaUpdateEvent();
    }

    public void initView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFragment2.setLayoutManager(manager);


        dbShouChangTicketBeanList = DBShouChangTicketBeanUtils.getInstance().queryDataDependUserName(userName);
        Log.i("aaa", "收藏夹中的收藏个数为：  " + dbShouChangTicketBeanList.size());
        if (dbShouChangTicketBeanList != null) {
            mTitlesLast = new String[dbShouChangTicketBeanList.size()];
            mIconUnselectIdsLast = new int[dbShouChangTicketBeanList.size()];
            for (int i = 0; i < dbShouChangTicketBeanList.size(); i++) {
                mTitlesLast[i] = mTitles[dbShouChangTicketBeanList.get(i).getPosition()];
                mIconUnselectIdsLast[i] = mIconUnselectIds[dbShouChangTicketBeanList.get(i).getPosition()];
            }
        }

        fragment2Adapter = new Fragment2Adapter(mTitlesLast, mIconUnselectIdsLast);
        rvFragment2.setAdapter(fragment2Adapter);
        fragment2Adapter.notifyDataSetChanged();
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(MediaUpdateEvent.class)
                .subscribe(new Action1<MediaUpdateEvent>() {
                    @Override
                    public void call(MediaUpdateEvent mediaUpdateEvent) {
                        if (ConstKey.BUY_DATA_SUCCESS.equals(mediaUpdateEvent.getMediaUpdateEvent())) {
                            dbShouChangTicketBeanList = DBShouChangTicketBeanUtils.getInstance().queryDataDependUserName(userName);
                            Log.i("aaa", "刷新的收藏夹中的收藏个数为：  " + dbShouChangTicketBeanList.size());
                            if (dbShouChangTicketBeanList != null) {
                                mTitlesLast = new String[dbShouChangTicketBeanList.size()];
                                mIconUnselectIdsLast = new int[dbShouChangTicketBeanList.size()];
                                for (int i = 0; i < dbShouChangTicketBeanList.size(); i++) {
                                    mTitlesLast[i] = mTitles[dbShouChangTicketBeanList.get(i).getPosition()];
                                    mIconUnselectIdsLast[i] = mIconUnselectIds[dbShouChangTicketBeanList.get(i).getPosition()];
                                }
                                fragment2Adapter = new Fragment2Adapter(mTitlesLast, mIconUnselectIdsLast);
                                rvFragment2.setAdapter(fragment2Adapter);
                            } else {
                                mTitlesLast = new String[0];
                                mIconUnselectIdsLast = new int[0];
                            }
                            fragment2Adapter.notifyDataSetChanged();
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


    class Fragment2Adapter extends RecyclerView.Adapter<Fragment2Adapter.Fragment2AdapterViewHolder> {

        private String[] mTitlesLast;

        private int[] mIconUnselectIdsLast;

        public Fragment2Adapter(String[] mTitlesLast, int[] mIconUnselectIdsLast) {
            this.mTitlesLast = mTitlesLast;
            this.mIconUnselectIdsLast = mIconUnselectIdsLast;
        }

        @Override
        public Fragment2AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_fragment2, parent, false);

            Fragment2AdapterViewHolder holder = new Fragment2AdapterViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(Fragment2AdapterViewHolder holder, final int position) {

           List<DBTicketBean> dbTicketBeanList = new ArrayList<>();
            dbTicketBeanList = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitlesLast[position]);
            holder.tvItemFragment2YuPiao.setText("余票数："+dbTicketBeanList.get(0).getJingDianYuPiao());

            holder.tvItemFragment2Title.setText(mTitlesLast[position]);
            Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), mIconUnselectIdsLast[position]);
            holder.ivItemFragment2Picture.setImageBitmap(bitmap);
            final List<DBTicketBean> finalDbTicketBeanList = dbTicketBeanList;
            holder.btnItemFragment2BuyTic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<DBTicketBean> dbTicketBeanList3 = new ArrayList<>();
                    dbTicketBeanList3 = DBTicketBeanUtils.getInstance().queryDataDependJingDianName(mTitles[position]);
                    if (dbTicketBeanList3.get(0).getJingDianYuPiao()>0) {
                        Log.i("aaa", "点击购买时的当前用户信息为： " + dbUserInfoBean.toString());
                        DBBuyTicketBean dbBuyTicketBean = new DBBuyTicketBean();
                        dbBuyTicketBean.setCreatTimeAsId(getTime());
                        dbBuyTicketBean.setUserName(userName);
                        dbBuyTicketBean.setBuyerName(dbUserInfoBean.getName());
                        dbBuyTicketBean.setBuyerOld(dbUserInfoBean.getOld());
                        dbBuyTicketBean.setBuyerTel(dbUserInfoBean.getTellPhone());
                        dbBuyTicketBean.setBuyerMail(dbUserInfoBean.getMail());
                        dbBuyTicketBean.setJingdainMing(mTitlesLast[position]);
                        dbBuyTicketBean.setPosition(dbShouChangTicketBeanList.get(position).getPosition());
                        Log.i("aaa", "购买票保存的信息为：  " + dbBuyTicketBean.toString());

                        DBBuyTicketBeanUtils.getInstance().insertOneData(dbBuyTicketBean);
                        //余票-1 start
                        finalDbTicketBeanList.get(0).jingDianYuPiao = (dbTicketBeanList3.get(0).jingDianYuPiao) - 1;
                        DBTicketBeanUtils.getInstance().updateData(dbTicketBeanList3.get(0));
                        //余票-1 end
                        DBShouChangTicketBean dbShouChangTicketBean = dbShouChangTicketBeanList.get(position);
                        DBShouChangTicketBeanUtils.getInstance().deleteOneData(dbShouChangTicketBean);
                        Toast.makeText(getActivity(), "恭喜您购票成功", Toast.LENGTH_SHORT).show();
                        RxBus.getDefault().post(new MediaUpdateEvent(ConstKey.BUY_DATA_SUCCESS));
                    }else {
                        Toast.makeText(getActivity(), "余票不足，无法购买", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.btnItemFragment2DeleteShouChangTic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBShouChangTicketBean dbShouChangTicketBean = dbShouChangTicketBeanList.get(position);
                    DBShouChangTicketBeanUtils.getInstance().deleteOneData(dbShouChangTicketBean);
                    RxBus.getDefault().post(new MediaUpdateEvent(ConstKey.BUY_DATA_SUCCESS));
                    Toast.makeText(getActivity(),"已成功从购物车删除",Toast.LENGTH_SHORT).show();
                }
            });
        }
        public long getTime() {
            return System.currentTimeMillis();//获取系统时间戳
        }
        @Override
        public int getItemCount() {
            return mTitlesLast == null ? 0 : mTitlesLast.length;
        }

        class Fragment2AdapterViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.ivItemFragment2Picture)
            ImageView ivItemFragment2Picture;
            @BindView(R.id.tvItemFragment2Title)
            TextView tvItemFragment2Title;
            @BindView(R.id.btnItemFragment2BuyTic)
            Button btnItemFragment2BuyTic;
            @BindView(R.id.btnItemFragment2DeleteShouChangTic)
            Button btnItemFragment2DeleteShouChangTic;
            @BindView(R.id.tvItemFragment2YuPiao)
            TextView tvItemFragment2YuPiao;

            public Fragment2AdapterViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
