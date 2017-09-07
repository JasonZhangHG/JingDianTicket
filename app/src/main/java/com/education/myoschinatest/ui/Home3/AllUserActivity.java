package com.education.myoschinatest.ui.Home3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.DBBeanUtils.RxBus;
import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBUserInfoBean;
import com.education.myoschinatest.bean.MediaUpdateEvent;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;


public class AllUserActivity extends AppCompatActivity {

    @BindView(R.id.rvAllUserActivity)
    RecyclerView rvAllUserActivity;
    private AllUserActivityAdapter allUserActivityAdapter;
    private List<DBUserInfoBean> dbUserInfoBeanList = new ArrayList<>();
    private Subscription rxSubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);
        ButterKnife.bind(this);
        dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryData();
        initView();
        refreshMediaUpdateEvent();
    }

    public void initView() {
        final LinearLayoutManager manager = new LinearLayoutManager(AllUserActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAllUserActivity.setLayoutManager(manager);
        allUserActivityAdapter = new AllUserActivityAdapter();
        rvAllUserActivity.setAdapter(allUserActivityAdapter);
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(MediaUpdateEvent.class)
                .subscribe(new Action1<MediaUpdateEvent>() {
                    @Override
                    public void call(MediaUpdateEvent mediaUpdateEvent) {
                        if (ConstKey.DELETE_SHOU_CHANG_DATA_SUCCESS.equals(mediaUpdateEvent.getMediaUpdateEvent())) {
                            dbUserInfoBeanList = DBUserInfoBeanUtils.getInstance().queryData();
                            allUserActivityAdapter.notifyDataSetChanged();
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

    class AllUserActivityAdapter extends RecyclerView.Adapter<AllUserActivityAdapter.AllUserActivityAdapterViewHolder> {

        @Override
        public AllUserActivityAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(AllUserActivity.this).inflate(R.layout.item_all_user_activity, parent, false);

            AllUserActivityAdapterViewHolder holder = new AllUserActivityAdapterViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(AllUserActivityAdapterViewHolder holder, final int position) {
            holder.tvItemAllUserActivityUserID.setText("用户ID为："+dbUserInfoBeanList.get(position).getCreatTimeAsId());
            holder.tvItemAllUserActivityUserName.setText("用户名为："+dbUserInfoBeanList.get(position).getUserName());
            holder.tvItemAllUserActivityPassWord.setText("用户密码为："+dbUserInfoBeanList.get(position).getPassWord());
            holder.btnItemAllUserActivityMoreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AllUserActivity.this,UserInfoActivity.class);
                    intent.putExtra("11",2);
                    intent.putExtra("userName",dbUserInfoBeanList.get(position).getUserName());
                    startActivity(intent);
                }
            });
            holder.btnItemAllUserActivityDeleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBUserInfoBean dbUserInfoBean = dbUserInfoBeanList.get(position);
                    DBUserInfoBeanUtils.getInstance().deleteOneData(dbUserInfoBean);
                    RxBus.getDefault().post(new MediaUpdateEvent(ConstKey.DELETE_SHOU_CHANG_DATA_SUCCESS));
                    Toast.makeText(AllUserActivity.this, "删除用户成功", Toast.LENGTH_SHORT).show();
                }
            });
            holder.btnItemAllUserActivityTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AllUserActivity.this,BuyTicketInfoActivity.class);
                    intent.putExtra("22",2);
                    intent.putExtra("userName2",dbUserInfoBeanList.get(position).getUserName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dbUserInfoBeanList == null ? 0 : dbUserInfoBeanList.size();
        }

        class AllUserActivityAdapterViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tvItemAllUserActivityUserID)
            TextView tvItemAllUserActivityUserID;
            @BindView(R.id.tvItemAllUserActivityUserName)
            TextView tvItemAllUserActivityUserName;
            @BindView(R.id.tvItemAllUserActivityPassWord)
            TextView tvItemAllUserActivityPassWord;
            @BindView(R.id.btnItemAllUserActivityMoreInfo)
            Button btnItemAllUserActivityMoreInfo;
            @BindView(R.id.btnItemAllUserActivityDeleteUser)
            Button btnItemAllUserActivityDeleteUser;
            @BindView(R.id.btnItemAllUserActivityTicket)
            Button btnItemAllUserActivityTicket;

            public AllUserActivityAdapterViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
