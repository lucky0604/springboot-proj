package com.lucky.account.repository;

import com.lucky.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lucky on 2/24/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
