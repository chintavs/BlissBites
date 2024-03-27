package com.myplantdiary.blissbites.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class
ShoppingCartItem {
    public ShoppingCartItem(Desert desert, int quantity) {
        this.desert = desert;
        this.quantity = quantity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Desert desert;
    private int quantity;
}
