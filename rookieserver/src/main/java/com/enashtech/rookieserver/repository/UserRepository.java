package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
