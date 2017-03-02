package com.lucky.dao;

import com.lucky.domain.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucky on 3/1/17.
 * 传统的jdbc操作中，需要手动管理数据库连接的开关，数据库资源访问的开关等
 * 这里采用Mybatis和Druid两个框架，可以不必理会数据库连接等等的控制
 *
 */
public interface UserDao extends Dao<User> {
    int add(User user);

    int del(User user);

    int update(User user);

    User findOneById(Serializable Id);

    List<User> findAll();

    /**
     * 查找到用户ID后，进行更新sessionId
     * @param userLogin 用户ID
     * @param userSessionId sessionId
     */
    void updateLoginSession(@Param("userLogin") String userLogin, @Param("userSessionId") String userSessionId);

    List<HashMap<String, String>> getUserMeta(@Param("userId") int userId);
}
