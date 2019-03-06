package com.yanghaoyi.myretrofit.presenter;

import com.yanghaoyi.myretrofit.model.EmailModel;
import com.yanghaoyi.myretrofit.view.ISearchView;
import com.yanghaoyi.net.bean.EmailData;
import com.yanghaoyi.net.client.OnGetDataListener;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :快递请求逻辑中心
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public class EmailPresenter {

    private EmailModel emailModel;
    private ISearchView emailView;


    public EmailPresenter(ISearchView emailView) {
        this.emailView = emailView;
        emailModel = new EmailModel();
    }

    /** 根据单号请求快递信息 */
    public void request(String number){
        emailView.addLoading();
        emailModel.request(number, new OnGetDataListener<EmailData>() {
            @Override
            public void success(EmailData response) {
                emailView.removeLoading();
                emailView.updateEmailMessage(response.getMessage());
            }

            @Override
            public void fail(EmailData response, String msg) {
                emailView.removeLoading();
                emailView.showError();
            }
        });
    }

}
