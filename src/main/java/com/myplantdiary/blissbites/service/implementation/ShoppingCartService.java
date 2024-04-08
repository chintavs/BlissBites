package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.repository.IShoppingCartDAO;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    IShoppingCartDAO shoppingCartDao;
    @Override
    public ShoppingCartItem addToShoppingCart(ShoppingCartItem shoppingCartItem) {
        return shoppingCartDao.save(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return (List<ShoppingCartItem>) shoppingCartDao.findAll();
    }

    @Override
    public ShoppingCartItem getShoppingCartItem(int id) {
        return shoppingCartDao.findById(id).get();
    }

    @Override
    public void deleteAll() {
        shoppingCartDao.deleteAll();
    }
}
