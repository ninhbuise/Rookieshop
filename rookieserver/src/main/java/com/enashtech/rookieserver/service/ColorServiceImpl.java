package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Color;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.repository.ColorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Color findByColorId(int id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundExecptionHandle("Could not found color: " + id));
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

}
