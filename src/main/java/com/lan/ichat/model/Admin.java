package com.lan.ichat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * package com.lan.ichat.model
 *
 * @author spoomlan
 * @date 28/12/2017
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = -8315001063328205558L;
    private Long id;
    private String username;
    private String password;
    private Date lastLoginTime;
    private String avatar;
    private String email;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
