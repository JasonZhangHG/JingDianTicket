package com.education.myoschinatest.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.ui.Home1.Fragment1;
import com.education.myoschinatest.ui.Home2.Fragment2;
import com.education.myoschinatest.ui.Home3.Fragment3;
import com.education.myoschinatest.ui.other.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
   private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";

    private CommonTabLayout bottomTabLayout;
    private String[] mTitles = {"景点","购物车","我的"};
    private int[] mIconUnselectIds = {R.mipmap.ic_find,R.mipmap.ic_listing,R.mipmap.ic_user};
    private int[] mIconSelectIds = {R.mipmap.ic_find_selected,R.mipmap.ic_listing_selected,R.mipmap.ic_user_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        bottomTabLayout= (CommonTabLayout) findViewById(R.id.main_tab_layout);

        initTab();
        initFragment(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    //用来初始化底部导航
    private void initTab() {
        for (int i = 0; i < mIconSelectIds.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        bottomTabLayout.setTabData(mTabEntities);
        bottomTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                    SwitchTo(position);

            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }


    //
    /**
     * 初始化碎片
     *  private BianMinFragment bianMinFragment;
     private CYFragment cyFragment;
     private ChatFragment ChatFragment;
     private jtFragment jtFragment;
     */
    public void initFragment(Bundle savedInstanceState){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition=0;
        if (savedInstanceState!=null){//判断保留的数据是否为空

            fragment1   = (Fragment1) getSupportFragmentManager().findFragmentByTag("fragment1");
            fragment2= (Fragment2) getSupportFragmentManager().findFragmentByTag("fragment2");
            fragment3= (Fragment3) getSupportFragmentManager().findFragmentByTag("fragment3");
            currentTabPosition = savedInstanceState.getInt(HOME_CURRENT_TAB_POSITION);
        }else {
            fragment1=Fragment1.instanceFragment();
            fragment2=Fragment2.instanceFragment();
            fragment3=Fragment3.instanceFragment();

            //transaction里添加fragment
            transaction.add(R.id.linearlayout_main,fragment1,"fragment1");
            transaction.add(R.id.linearlayout_main,fragment2,"fragment2");
            transaction.add(R.id.linearlayout_main,fragment3,"fragment3");


        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        bottomTabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case 0:
                transaction.show(fragment1);
                transaction.hide(fragment2);
                transaction.hide(fragment3);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(fragment2);
                transaction.hide(fragment1);
                transaction.hide(fragment3);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(fragment3);
                transaction.hide(fragment2);
                transaction.hide(fragment1);
                transaction.commitAllowingStateLoss();
                break;
        }

    }


}