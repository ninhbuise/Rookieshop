package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    
}
