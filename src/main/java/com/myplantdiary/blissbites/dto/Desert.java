package com.myplantdiary.blissbites.dto;

import lombok.Data;

@Data
public class Desert {
    private int id;
    private String name;
    private int stockCount;
    private double cost;
    private String description;
    private String type;
}
