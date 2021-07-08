package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Store;

public interface StoreService {
   Store findByNameOwner(String owner);
}
