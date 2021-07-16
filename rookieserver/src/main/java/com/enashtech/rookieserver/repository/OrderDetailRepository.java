package com.enashtech.rookieserver.repository;

import java.util.List;

import com.enashtech.rookieserver.entity.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Transactional
    @Query(" SELECT od FROM OrderDetail od "
	        + "join Product p on p.id = od.product "
	        + "join Store s on s.id = p.store "
	        + "where s.name like %:name%")
    List<OrderDetail> getListProductsByStoreName(@Param("name") String name);
}
