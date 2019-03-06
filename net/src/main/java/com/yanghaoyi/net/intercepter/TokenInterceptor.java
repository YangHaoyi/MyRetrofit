package com.yanghaoyi.net.intercepter;

import android.util.Log;

import com.google.gson.Gson;
import com.yanghaoyi.net.TokenManager;
import com.yanghaoyi.net.api.AdvanceAPI;
import com.yanghaoyi.net.bean.HttpResult;
import com.yanghaoyi.net.bean.TokenInfo;
import com.yanghaoyi.net.client.ApiClient;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class TokenInterceptor implements Interceptor {

    private static final String TAG = "MyRetrofit";
    private static final String TOKEN = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = Util.UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(Util.UTF_8);
        }
        String bodyStr = buffer.clone().readString(charset);
        Gson gson = new Gson();
        HttpResult httpResult = gson.fromJson(bodyStr, HttpResult.class);
        String requestUrl=request.url().encodedPath();
        Log.d(TAG,"Request_is:"+requestUrl);
        //同步代码块，当在刷新token的时候暂停其他的request，锁为当前类的单例对象
        synchronized (ApiClient.INSTANCE){
            //比较请求的token与本地存储的token   如果不一致还是直接重试
            String request_token=request.header(TOKEN);
            String access_token= TokenManager.getToken();
            if(request_token!=null&&access_token!=null&&!request_token.equals(access_token)){
                Log.d(TAG,"Request_retry:"+requestUrl);
                //等待的request重新拼装请求头
                Request newRequest=request.newBuilder().header("token", TokenManager.getToken()).build();
                return chain.proceed(newRequest);//重试request
            }
            if (null!=httpResult.getErr()&&httpResult.getErr().endsWith("auth fail")){
                Log.d(TAG,"RefreshToken");
                String token = getNewToken();
                Log.d(TAG,"RefreshToken_finish"+token);
                //保存token
                TokenManager.setToken(token);
                //重新拼装请求头
                Request newRequest=request.newBuilder().header("token", TokenManager.getToken()).build();
                //重试request
                return chain.proceed(newRequest);
            }
        }
        Log.d(TAG,"Request_end"+requestUrl);
        return originalResponse;
    }


    private String getNewToken() throws IOException {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new LoggingInterceptor());

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://mytest.cc.yhy")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
        retrofit2.Response<TokenInfo> tokenJson = retrofit.create(AdvanceAPI.class).refreshToken().execute();
        String headerToken = tokenJson.body().getData().getToken();
        return headerToken;
    }

}
