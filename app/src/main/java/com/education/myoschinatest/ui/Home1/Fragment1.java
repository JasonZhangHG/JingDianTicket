package com.education.myoschinatest.ui.Home1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;

public class Fragment1 extends BaseFragment {


    private RecyclerView rvFragment1;

    private String[] mTitles = {"白帝城景点", "大足石刻", "水龙峡地缝", "武隆天生三桥", "仙女山国家深林公园"};

    private int[] mIconUnselectIds = {R.drawable.bai_di_cheng, R.drawable.da_zhu_shi_ke, R.drawable.sui_long_xia, R.drawable.wu_long, R.drawable.xian_nv_shan};

    private Fragment1Adapter fragment1Adapter;

    public static Fragment1 instanceFragment() {
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        rvFragment1 = (RecyclerView) view.findViewById(R.id.rvFragment1);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFragment1.setLayoutManager(manager);
        fragment1Adapter = new Fragment1Adapter(mTitles,mIconUnselectIds);
        rvFragment1.setAdapter(fragment1Adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment1;
    }

    class Fragment1Adapter extends RecyclerView.Adapter<Fragment1Adapter.Fragment1AdapterViewHolder> {

        private  String[] mTitles ;

        private int[] mIconUnselectIds ;

        public Fragment1Adapter(String[] mTitles,int[] mIconUnselectIds){
            this.mTitles = mTitles;
            this.mIconUnselectIds = mIconUnselectIds;
        }
        @Override
        public Fragment1AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_fragment1, parent, false);

            Fragment1AdapterViewHolder holder = new Fragment1AdapterViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(Fragment1AdapterViewHolder holder, final int position) {
            holder.tvItemFragment1Title.setText(mTitles[position]);
            Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), mIconUnselectIds[position]);
            holder.ivItemFragment1Picture.setImageBitmap(bitmap);
            holder.ivItemFragment1Picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),JingDianInfoActivity.class);
                    intent.putExtra("JingDianPosition",position);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTitles.length;
        }

        class Fragment1AdapterViewHolder extends RecyclerView.ViewHolder {
           private ImageView ivItemFragment1Picture;
           private TextView tvItemFragment1Title;
           private TextView tvItemFragment1YuPiao;
            public Fragment1AdapterViewHolder(View itemView) {
                super(itemView);
                ivItemFragment1Picture = (ImageView) itemView.findViewById(R.id.ivItemFragment1Picture);
                tvItemFragment1Title = (TextView) itemView.findViewById(R.id.tvItemFragment1Title);
                tvItemFragment1YuPiao = (TextView) itemView.findViewById(R.id.tvItemFragment1YuPiao);
            }
        }
    }
}
