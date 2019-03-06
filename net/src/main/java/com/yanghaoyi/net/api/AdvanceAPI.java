package com.yanghaoyi.net.api;

import com.yanghaoyi.net.bean.EmailData;
import com.yanghaoyi.net.bean.NearbyData;
import com.yanghaoyi.net.bean.TokenInfo;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :请求Api
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public interface AdvanceAPI {

    @POST("/EmailSearch")
    Observable<EmailData> emailSearch(@Body RequestBody param);

    @POST("my_test/get_token")
    Call<TokenInfo> refreshToken();

    @POST("my_test/query_nearby")
    Observable<NearbyData> requestNearby(@QueryMap Map<String, String> map);
}
