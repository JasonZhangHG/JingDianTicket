package com.education.myoschinatest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 可以嵌入 ScrollView 的ListView
 * 不推荐经常使用,影响 ListView的性能
 *
 * Activity
 *  Fragmnet
 *    ScrollView
 *    LinearLayout
 *       ListView
 *           ViewGrop
 *              ViewGrop
 *
 * addHeader()
 * getItemViewType()
 *
 * Created by wanggang on 2016/10/9.
 */

public class ListViewInScroll extends ListView {
    public ListViewInScroll(Context context) {
        super(context);
    }

    public ListViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewInScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
