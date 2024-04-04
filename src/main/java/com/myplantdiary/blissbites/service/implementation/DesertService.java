package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.repository.IDesertDAO;
import com.myplantdiary.blissbites.service.interfaces.IDesertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesertService implements IDesertService {
    @Autowired
    IDesertDAO desertDao;
    @Override
    public List<Desert> getAllDeserts() {
        return (List<Desert>) desertDao.findAll();
    }

    @Override
    public Desert getDesertById(int Id) {
        return desertDao.findById(Id).get();
    }

}
