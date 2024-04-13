package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.repository.IShoppingCartDAO;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ShoppingCartServiceStub implements IShoppingCartService {

    IShoppingCartDAO shoppingCartDao;

    public ShoppingCartServiceStub(IShoppingCartDAO shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public ShoppingCartItem addToShoppingCart(ShoppingCartItem shoppingCartItem) {
        return shoppingCartDao.save(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        List<ShoppingCartItem> allCartItems = new ArrayList<>();
        Iterable<ShoppingCartItem> cartItems = shoppingCartDao.findAll();
        for(ShoppingCartItem cartItem : cartItems){
            allCartItems.add(cartItem);
        }
        return allCartItems;
    }

    @Override
    public ShoppingCartItem getShoppingCartItem(int id) {
        try {
            return shoppingCartDao.findById(id).get();
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        shoppingCartDao.deleteAll();
    }
}
