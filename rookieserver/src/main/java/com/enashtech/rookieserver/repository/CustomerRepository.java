package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Transactional
    @Query("select c from Customer c "
            + "join User u on u = c.user "
            + "where u.username like %:username%")
    Customer findByUserName(@Param("username") String username);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
}
