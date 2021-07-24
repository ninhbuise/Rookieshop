package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.Size;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.SizeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Autowired
    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Size findSizeById(int id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new NotFoundExecptionHandle("Could not found size: " + id));
    }

}
