package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;

import java.util.List;

public class ShoppingCartService implements IShoppingCartService {
    @Override
    public boolean addToShoppingCart(Desert desert, int quantity) {
        return false;
    }

    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return null;
    }

    @Override
    public ShoppingCartItem getShoppingCartItem(Desert desert) {

        return null;
    }
}
