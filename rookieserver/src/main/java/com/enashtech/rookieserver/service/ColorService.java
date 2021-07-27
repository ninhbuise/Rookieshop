package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Color;

public interface ColorService {
    Color findByColorId(int id);
    List<Color> getAllColors();
}
