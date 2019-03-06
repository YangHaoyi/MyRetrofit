package com.yanghaoyi.myretrofit.view.impl;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghaoyi.myretrofit.R;
import com.yanghaoyi.myretrofit.view.ISearchView;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :快递请求视图实现
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public class SearchViewImpl implements ISearchView {

    /** 搜索结果 */
    private TextView tvResponse;
    /** 上下文 */
    private FragmentActivity context;
    /** 根布局 */
    private FrameLayout fmContent;
    /** Loading视图 */
    private View fmLoading;

    public SearchViewImpl(FragmentActivity context, FrameLayout fmContent, TextView tvResponse) {
        this.context = context;
        this.fmContent = fmContent;
        this.tvResponse = tvResponse;
    }

    /** 更新文字显示 */
    @Override
    public void updateEmailMessage(String message) {
        tvResponse.setText(message);
    }


    /** 搜索失败 */
    @Override
    public void showError() {
        Toast.makeText(context,context.getResources().getString(R.string.search_fail),Toast.LENGTH_LONG).show();
    }

    /** 添加遮罩 */
    @Override
    public void addLoading() {
        removeLoading();
        if(null!=fmContent){
            fmLoading = context.getLayoutInflater().inflate(R.layout.comp_loading,null);
            fmContent.addView(fmLoading);
        }
    }

    /** 移除遮罩 */
    @Override
    public void removeLoading() {
        if(null!=fmLoading&&null!=fmContent){
            fmContent.removeView(fmLoading);
            fmLoading = null;
        }
    }
}
