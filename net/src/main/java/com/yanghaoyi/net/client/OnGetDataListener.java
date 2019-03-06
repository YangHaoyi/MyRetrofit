package com.yanghaoyi.net.client;

/**
 * @author YangHaoyi on 2019/3/4.
 *         Email  : yanghaoyi@qq.com.
 *         Description :请求监听
 *         Change : YangHaoYi on 2019/3/4.
 *         Version :V 1.1
 */
public interface OnGetDataListener<T> {

    /** 请求成功 */
    void success(T response);

    /** 请求失败 */
    void fail(T response, String msg);
}
