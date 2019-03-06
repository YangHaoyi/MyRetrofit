package com.yanghaoyi.myretrofit.model;

import com.yanghaoyi.net.api.AdvanceAPI;
import com.yanghaoyi.net.client.ApiClient;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class BaseModel {

    protected AdvanceAPI api;

    public BaseModel() {
        ApiClient.INSTANCE.init();
        api = ApiClient.INSTANCE.getApi();
    }
}
