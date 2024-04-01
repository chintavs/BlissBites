package com.myplantdiary.blissbites.repository;

import com.myplantdiary.blissbites.dto.Desert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface IDesertDAO extends CrudRepository<Desert, Integer> {

}
