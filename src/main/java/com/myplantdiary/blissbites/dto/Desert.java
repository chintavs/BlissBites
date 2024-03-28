package com.myplantdiary.blissbites.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Desert {
    @Id
    private int id;
    private String name;
    private int stockCount;
    private double cost;
    private String description;
    private String type;
    private String imageLink;
}
