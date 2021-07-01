package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}
