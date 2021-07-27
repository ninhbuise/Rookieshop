package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.Size;

public interface SizeService {
    Size findSizeById(int id);
    List<Size> getAllSize();
}
