package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
