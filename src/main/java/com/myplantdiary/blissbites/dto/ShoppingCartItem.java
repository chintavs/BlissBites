package com.myplantdiary.blissbites.dto;

import lombok.Data;

@Data
public class ShoppingCartItem {
    private int id;
    private Desert desert;
    private int quantity;
}
