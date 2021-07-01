package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{
    
}
