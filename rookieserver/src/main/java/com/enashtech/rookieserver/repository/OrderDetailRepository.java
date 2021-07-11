package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    
}
