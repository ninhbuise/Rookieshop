package com.enashtech.rookieserver.repository;

import java.util.List;


import com.enashtech.rookieserver.entity.Product;
import com.enashtech.rookieserver.entity.ProductDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Transactional
    @Modifying
    @Query("select p from Product p "
            + "join Store s on s  = p.store "
            + "join User u on u = s.user "
            + "where u.username like %:owner%")
    List<ProductDTO> getListProductsByNameOwner(@Param("owner") String owner);
}
