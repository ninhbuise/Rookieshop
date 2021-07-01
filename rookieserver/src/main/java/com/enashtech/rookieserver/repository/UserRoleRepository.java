package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    
}
