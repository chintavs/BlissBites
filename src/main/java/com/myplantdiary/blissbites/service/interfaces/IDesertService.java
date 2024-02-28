package com.myplantdiary.blissbites.service.interfaces;

import com.myplantdiary.blissbites.dto.Desert;

import java.util.List;

public interface IDesertService {
    public List<Desert> getAllDeserts();
    public boolean saveDesert(Desert desert);
    public boolean editDesert(Desert desert);
    public boolean addDesert(Desert desert, int quantity);
}
