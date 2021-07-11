package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReivewRepository extends JpaRepository<Review, Integer> {
    
}
