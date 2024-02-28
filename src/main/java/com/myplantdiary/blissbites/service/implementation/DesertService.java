package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.service.interfaces.IDesertService;

import java.util.List;

public class DesertService implements IDesertService {
    @Override
    public List<Desert> getAllDeserts() {
        return null;
    }

    @Override
    public boolean saveDesert(Desert desert) {
        return false;
    }

    @Override
    public boolean editDesert(Desert desert) {
        return false;
    }

    @Override
    public boolean addDesert(Desert desert, int quantity) {
        return false;
    }
}
