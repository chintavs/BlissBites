package com.myplantdiary.blissbites.service.interfaces;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;

import java.util.List;

public interface IShoppingCartService {
    ShoppingCartItem addToShoppingCart(ShoppingCartItem shoppingCartItem);
    List<ShoppingCartItem> getAllShoppingCartItems();
    ShoppingCartItem getShoppingCartItem(int id);
    void deleteAll();
}
