package com.myplantdiary.blissbites.service.interfaces;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;

import java.util.List;

public interface IShoppingCartService {
    public boolean addToShoppingCart(Desert desert, int quantity);
    public List<ShoppingCartItem> getAllShoppingCartItems();
}
