package com.lan.ichat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * package com.lan.ichat.model
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -6392066827261775844L;
    private Long id;
    private String username;
    private String nickname;
    private String telephone;
    private String motto;
    private String password;
    private String avatar;
    private Date createTime;
    private Boolean online;
    private Boolean enabled;
    private Date lastLoginTime;
    private Integer gender;
    private String region;
    private String email;

    private Integer type;

    private Integer hideMyMM;
    private Integer hideHisMM;
    private Integer star;
    private Integer blacklist;
    private Integer chatroom;

    public Integer getChatroom() {
        return chatroom;
    }

    public void setChatroom(Integer chatroom) {
        this.chatroom = chatroom;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHideMyMM() {
        return hideMyMM;
    }

    public void setHideMyMM(Integer hideMyMM) {
        this.hideMyMM = hideMyMM;
    }

    public Integer getHideHisMM() {
        return hideHisMM;
    }

    public void setHideHisMM(Integer hideHisMM) {
        this.hideHisMM = hideHisMM;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Integer blacklist) {
        this.blacklist = blacklist;
    }
}
