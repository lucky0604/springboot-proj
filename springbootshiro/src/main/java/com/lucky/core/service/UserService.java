package com.lucky.core.service;

import com.lucky.core.bean.User;

/**
 * Created by lucky on 17-1-11.
 */
public interface UserService {
    /**
     * 通过username查找用户信息
     */
    public User findByUsername(String username);
}
