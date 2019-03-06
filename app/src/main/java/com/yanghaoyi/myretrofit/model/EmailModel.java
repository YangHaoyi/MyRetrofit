package com.yanghaoyi.myretrofit.model;

import com.google.gson.Gson;
import com.yanghaoyi.myretrofit.model.params.EmailParams;
import com.yanghaoyi.net.bean.EmailData;
import com.yanghaoyi.net.client.ApiClient;
import com.yanghaoyi.net.client.OnGetDataListener;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :快递请求数据控制中心
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public class EmailModel {

    public EmailModel() {
        ApiClient.INSTANCE.init();
    }


    /** 发起请求 */
    public void request(String number, final OnGetDataListener<EmailData> listener){
        EmailParams params = new EmailParams();
        params.setNumber(number);
        Gson gson = new Gson();
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(params));

        Observable<EmailData> response = ApiClient.INSTANCE.getApi().emailSearch(body);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EmailData>() {
                    @Override
                    public void onCompleted() {
                        //no use
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.fail(null,"");
                    }

                    @Override
                    public void onNext(EmailData emailData) {
                        listener.success(emailData);
                    }
                });
    }


}
