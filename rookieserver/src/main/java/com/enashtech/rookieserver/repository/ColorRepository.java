package com.enashtech.rookieserver.repository;

import com.enashtech.rookieserver.entity.Color;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer>{
    
}
