package com.enashtech.rookieserver.repository;

import java.util.Optional;

import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(RoleName roleName);
}
