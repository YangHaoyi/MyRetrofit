package com.yanghaoyi.net.bean;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class HttpResult {
    private int status;
    private String err;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
