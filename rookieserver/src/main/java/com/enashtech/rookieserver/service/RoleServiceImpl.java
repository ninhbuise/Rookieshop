package com.enashtech.rookieserver.service;

import java.util.Optional;

import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return repository.findByName(roleName);
    }
    
}
