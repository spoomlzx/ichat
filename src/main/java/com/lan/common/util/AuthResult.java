package com.lan.common.util;

/**
 * package com.lan.common.util
 *
 * @author lanzongxiao
 * @date 2017/11/1
 */
public class AuthResult extends BaseResult {

    private String token;

    public AuthResult(){
        super();
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
