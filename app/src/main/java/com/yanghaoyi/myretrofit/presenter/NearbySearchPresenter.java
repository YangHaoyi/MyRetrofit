package com.yanghaoyi.myretrofit.presenter;

import com.yanghaoyi.myretrofit.model.NearbyModel;
import com.yanghaoyi.myretrofit.view.ISearchView;
import com.yanghaoyi.net.bean.NearbyData;
import com.yanghaoyi.net.client.OnGetDataListener;

/**
 * @author YangHaoyi on 2019/3/6.
 *         Email  : yanghaoyi@qq.com.
 *         Description :
 *         Change : YangHaoYi on 2019/3/6.
 *         Version :
 */
public class NearbySearchPresenter {

    private NearbyModel nearbyModel;
    private ISearchView searchView;


    public NearbySearchPresenter(ISearchView searchView) {
        this.searchView = searchView;
        nearbyModel = new NearbyModel();
    }


    public void request(){
        nearbyModel.request(new OnGetDataListener<NearbyData>() {
            @Override
            public void success(NearbyData response) {
                searchView.removeLoading();
                searchView.updateEmailMessage("搜索成功");
            }

            @Override
            public void fail(NearbyData response, String msg) {
                searchView.removeLoading();
                searchView.showError();
            }
        });
    }

}
