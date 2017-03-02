package com.lucky.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lucky on 3/1/17.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 123123213321312412L;
    private String id;
    private String userLogin;
    private String userPass;
    private String userNicname;
    private String userEmail;
    private String userUrl;
    private Date userRegistered;
    private String userActivationKey;
    private Integer userStatus;
    private String displayName;
    private String userSessionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNicname() {
        return userNicname;
    }

    public void setUserNicname(String userNicname) {
        this.userNicname = userNicname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public Date getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    public String getUserActivationKey() {
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userNicname='" + userNicname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", userRegistered=" + userRegistered +
                ", userActivationKey='" + userActivationKey + '\'' +
                ", userStatus=" + userStatus +
                ", displayName='" + displayName + '\'' +
                ", userSessionId='" + userSessionId + '\'' +
                '}';
    }
}
