package com.yanghaoyi.myretrofit.model;

import com.yanghaoyi.net.TokenManager;
import com.yanghaoyi.net.bean.NearbyData;
import com.yanghaoyi.net.client.ApiClient;
import com.yanghaoyi.net.client.OnGetDataListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class NearbyModel extends BaseModel{

    public void request(final OnGetDataListener<NearbyData> listener){
        Map<String, String> map = new HashMap<>();
        map.put("r","5000");
        map.put("lng","116.405419");
        map.put("lat","39.921133");
        map.put("page_index","1");
        map.put("page_size","300");
        map.put("user_lng","116.42705");
        map.put("user_lng","116.42705");
        map.put("user_lat","39.90339");
        map.put("user_id","hg11b");
        map.put("user_os_type","2");
        map.put("token", TokenManager.getToken());
        Observable<NearbyData> response = ApiClient.INSTANCE.getApi().requestNearby(map);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NearbyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.fail(null,"");
                    }

                    @Override
                    public void onNext(NearbyData nearbyData) {
                        listener.success(nearbyData);
                    }
                });
    }
}
