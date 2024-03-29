package com.myplantdiary.blissbites.service.interfaces;

import com.myplantdiary.blissbites.dto.Desert;

import java.util.List;
import java.util.Optional;

public interface IDesertService {
    List<Desert> getAllDeserts();
    Desert getDesertById(int Id);
}
