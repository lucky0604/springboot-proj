package com.lucky.account.service;

import com.lucky.account.model.User;

/**
 * Created by lucky on 2/27/17.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
