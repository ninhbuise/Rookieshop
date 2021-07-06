package com.enashtech.rookieserver.service;

import java.util.Optional;

import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.entity.RoleName;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}
