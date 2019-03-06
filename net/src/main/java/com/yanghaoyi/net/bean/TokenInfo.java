package com.yanghaoyi.net.bean;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class TokenInfo {

    private int status;
    private PayloadData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PayloadData getData() {
        return data;
    }

    public void setData(PayloadData data) {
        this.data = data;
    }

    public static class PayloadData{
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
