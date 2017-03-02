package com.lucky.service.userService;

import com.lucky.domain.User;
import com.lucky.service.BaseService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucky on 3/1/17.
 */
public interface UserService extends BaseService<User> {
    @Override
    void add(User user) throws Exception;

    @Override
    List<User> findAll(int pageNum, int pageSize);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @param metaKeys 需要被查询的键组
     * @return
     */
    List<HashMap<String, String>> getUserMeta(int userId, String... metaKeys);

    @Override
    User findOneById(Serializable id);

    /**
     * 根据用户id写入sessionId
     *
     * @param userId 用户ID
     * @param sessionId sessionId
     */
    void updateUserSession(String userId, String sessionId);

}
