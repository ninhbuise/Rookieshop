package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>{
    
}
