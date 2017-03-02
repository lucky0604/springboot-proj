package com.lucky.service.userService;

import com.lucky.dao.UserDao;
import com.lucky.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucky on 3/1/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) throws Exception {}

    @Override
    public List<User> findAll(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getUserMeta(int userId, String... metaKeys) {
        List<HashMap<String, String>> userMeta = new ArrayList<>();
        List<HashMap<String, String>> tmp = userDao.getUserMeta(userId);
        if (null == metaKeys || metaKeys.length == 0) {
            metaKeys = new String[]{"nickname", "description"};
        }

        // 先获取metaKey, 再遍历用户的meta数据获取需要的key的值，然后将他们成对存入集合中
        for (String key: metaKeys) {
            for (HashMap<String, String> map: tmp) {
                if (map.toString().contains(key)) userMeta.add(map);
            }
        }
        return userMeta;
    }

    @Override
    public User findOneById(Serializable id) {
        return userDao.findOneById(id);
    }

    @Override
    public void updateUserSession(String userId, String sessionId) {
        userDao.updateLoginSession(userId, sessionId);
    }


}
