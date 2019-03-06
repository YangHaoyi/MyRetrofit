package com.yanghaoyi.myretrofit.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yanghaoyi.myretrofit.R;
import com.yanghaoyi.myretrofit.presenter.EmailPresenter;
import com.yanghaoyi.myretrofit.presenter.NearbySearchPresenter;
import com.yanghaoyi.myretrofit.view.ISearchView;
import com.yanghaoyi.myretrofit.view.impl.SearchViewImpl;
/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :快递请求视图实现
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public class MyRetrofitActivity extends AppCompatActivity {

    /** 根布局 */
    private FrameLayout fmContent;
    /** 请求按钮 */
    private TextView tvRequest;
    /** 请求返回 */
    private TextView tvResponse;
    /** 逻辑控制中心 */
    private EmailPresenter presenter;
    private NearbySearchPresenter nearbySearchPresenter;

    /** 视图接口 */
    private ISearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /** 初始化 */
    private void init(){
        initView();
        initEvent();
    }

    /** 初始化View */
    private void initView(){
        fmContent = (FrameLayout) findViewById(android.R.id.content);
        tvRequest = (TextView) findViewById(R.id.tvRequest);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
        searchView = new SearchViewImpl(this,fmContent,tvResponse);
    }

    /** 初始化事件 */
    private void initEvent(){
        presenter = new EmailPresenter(searchView);
        nearbySearchPresenter = new NearbySearchPresenter(searchView);
        tvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                presenter.request("1012002");
                nearbySearchPresenter.request();
                searchView.updateEmailMessage("请求中...");
            }
        });
    }

}
