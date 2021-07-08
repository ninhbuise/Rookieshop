package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Store;
import com.enashtech.rookieserver.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceimpl implements StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceimpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store findByNameOwner(String owner) {
       return storeRepository.findByNameOwner(owner);
    }
    
}
