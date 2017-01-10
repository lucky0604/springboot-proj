package com.lucky.core.repository;

import com.lucky.core.bean.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lucky on 17-1-10.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 通过username查找用户信息
     */
    public User findByUsername(String username);
}
