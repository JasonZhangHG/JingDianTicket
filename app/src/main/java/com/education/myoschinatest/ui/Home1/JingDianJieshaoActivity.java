package com.education.myoschinatest.ui.Home1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.education.myoschinatest.R;

public class JingDianJieshaoActivity extends AppCompatActivity {

   private WebView wvJingDianJieshaoActivity;

    private int position;

    private String[] jingDianURL = {"http://baike.baidu.com/link?url=WPAfD8poFFPFOr_GvbniH1DS9gWMxkdSj7h4pNJegcJjyWyuEiDZNYuOxXltbUDbGNsiNrYd-7pOYCeBEezmxpqspAP_Qyrc_gHbK0ubWG-EXKnPe_JFOG5BH_PTBV09a3obAXCJ3-gE6CCpCUtxp_",
            "http://baike.baidu.com/link?url=KVf6M20-o2m_-lZOyLE0b9VVP5bkjM3dHQeKlVryd4E_3bGIvr9-X5NmJJrWEWzJbQRmWv1Xm9cbYl8396Tk17zCt_F3kW7rjojr_c8tEBYvcur7vXOuHOp8r1g5-EIE",
            "http://baike.baidu.com/link?url=37TjvBX-pCrNnYbshUXjlSYH6PDcGwyGSGST5K8bIPbbZwtMUQBso_i0oU8Dgp3ggHw3fdEgytH1yQAcGEHZQcBlndrksfVo23AGwpL0RkxJDaGEpAL050Pyjlu5ZsX-_H9O5yD9-qalXTyaTHKi7K",
            "http://baike.baidu.com/link?url=NnDDzx9PohnILQPBg0F-AO5cXMXPYxm2_OCIL_ecYYXFGKYybcMF_yxbd0Ifpol7-OY-W_KHZEkddqL3eTupEpGOspJZa33qPMzb-w__EjOKSux-wTiXSO3ie20FR5MqO4gyzkUx53aDsgcWJ9OI2itF1zNavq4zOOsQeRq1W4nm9S6WPRTYoaT-jG-vun6RI1EQkECCDhz0IK_oxAdUdK",
            "http://baike.baidu.com/link?url=XGNrfjGBlk12ojXqIWSFkv1B0UFPeBsKHgc2TO8Kil5QTVXddFuSooKCJ4a0Mn9fOiO7VScxtn1bCJoazEVu8peyP_jrD9MZhpto9HBGiUIpwblTn5-2t9mLwgwfrtR9Z7Yg7Lxtr9XIaTzSZJAFphw_ESdjNMz3wCnsjTPtElis6yXpd2qCdMREgEEtKX1Y"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_dian_jieshao);
        wvJingDianJieshaoActivity = (WebView) findViewById(R.id.wvJingDianJieshaoActivity);
        position = getIntent().getIntExtra("JingDianURL", 0);
        Log.i("aaa","位置为："+position);
        initWebView();
    }

    public void initWebView(){
        wvJingDianJieshaoActivity.loadUrl(jingDianURL[position]);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvJingDianJieshaoActivity.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wvJingDianJieshaoActivity.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        wvJingDianJieshaoActivity.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}

