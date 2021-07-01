package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer>{
    
}
