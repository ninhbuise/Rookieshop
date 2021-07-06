package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
}
