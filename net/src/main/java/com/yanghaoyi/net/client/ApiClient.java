package com.yanghaoyi.net.client;

import com.yanghaoyi.net.api.AdvanceAPI;
import com.yanghaoyi.net.intercepter.LoggingInterceptor;
import com.yanghaoyi.net.intercepter.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :请求Client
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public enum  ApiClient {

    /** client单例 */
    INSTANCE;

    /** 请求域名 */
    private static final String BASE_URL = "https://mytest.cc.yhy";
    private AdvanceAPI api;

    /** 初始化 */
    public void init(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new TokenInterceptor());
        okHttpClient.addInterceptor(new LoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(AdvanceAPI.class);
    }

    /** 获取API */
    public AdvanceAPI getApi() {
        return api;
    }

}
