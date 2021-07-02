package com.enashtech.rookieserver.repository;

import java.util.Optional;

import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    Optional<UserRole> findByName(RoleName roleName);
}
