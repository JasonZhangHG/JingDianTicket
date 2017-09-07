package com.education.myoschinatest.config;



public class UrlConfig {

    //baseUrl
    //测试地址
    //线上地址


    public static class Path{
        public static final String BASE_URL="http://capi.douyucdn.cn/";
        public static final String URL_VERTICAL="api/v1/getVerticalRoom";
    }
     public static class Key{
         public static final String LIMIT="limit";
         public static final String OFFSET="offset";
     }
    public static class DefaultValue{
        public static final String LIMIT="20";
        public static final String OFFSET="0";
    }
}
