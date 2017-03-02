package com.lucky.account.service;

/**
 * Created by lucky on 2/27/17.
 */
public interface SecuritySevice {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
