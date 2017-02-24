package com.lucky.account.repository;

import com.lucky.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucky on 2/24/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
