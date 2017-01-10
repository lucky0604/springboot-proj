package com.lucky.core.service.impl;

import com.lucky.core.bean.User;
import com.lucky.core.repository.UserRepository;
import com.lucky.core.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lucky on 17-1-11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername");
        return userRepository.findByUsername(username);
    }
}
