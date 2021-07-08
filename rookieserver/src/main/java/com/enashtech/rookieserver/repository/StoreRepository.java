package com.enashtech.rookieserver.repository;


import com.enashtech.rookieserver.entity.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StoreRepository extends JpaRepository<Store, Integer>{
    @Transactional
    @Query("select s from Store s "
            + "join User u on u = s.user "
            + "where u.username like %:owner%")
    Store findByNameOwner(@Param("owner") String owner);
}
