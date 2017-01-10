package com.lucky.dao;

import com.lucky.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lucky on 1/9/17.
 */
public interface UserDao extends CrudRepository<User, Integer> {
    public Iterable<User> findByUsername(String username);
    List<User> findAll();
    // 根据id删除用户，需要在接口类定义findById()
    //public Iterable<User> findById(int id);
}