package com.yanghaoyi.net;

/**
 * @author : YangHaoYi on 2019/3/6.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2019/3/6.
 * Version : V 1.0
 */
public class TokenManager {

    private static String token = "";

    public static String getToken() {
        return token;
    }

    public static void setToken(String saveToken) {
        token = saveToken;
    }
}
