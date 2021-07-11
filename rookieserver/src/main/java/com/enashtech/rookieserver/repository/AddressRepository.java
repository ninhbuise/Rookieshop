package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
