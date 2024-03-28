package com.myplantdiary.blissbites.service.interfaces;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface IShoppingCartService {
    public ShoppingCartItem addToShoppingCart(Desert desert, int quantity);
    public List<ShoppingCartItem> getAllShoppingCartItems();
    public Optional<ShoppingCartItem> getShoppingCartItem(int id);
}
