package com.enashtech.rookieserver.repository;

import java.util.Optional;

import com.enashtech.rookieserver.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
}
