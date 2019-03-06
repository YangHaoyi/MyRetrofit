package com.yanghaoyi.myretrofit.view;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :快递请求视图接口
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public interface ISearchView {

    /** 更新文字显示 */
    void updateEmailMessage(String message);

    /** 搜索失败 */
    void showError();

    /** 添加遮罩 */
    void addLoading();

    /** 移除遮罩 */
    void removeLoading();
}
