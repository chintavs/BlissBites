package com.myplantdiary.blissbites.service.implementation;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.repository.IShoppingCartDAO;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    IShoppingCartDAO shoppingCartDao;
    @Override
    public ShoppingCartItem addToShoppingCart(Desert desert, int quantity) {
        return shoppingCartDao.save(new ShoppingCartItem(desert, quantity));
    }

    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return (List<ShoppingCartItem>) shoppingCartDao.findAll();
    }

    @Override
    public Optional<ShoppingCartItem> getShoppingCartItem(int id) {
        return shoppingCartDao.findById(id);
    }

    @Override
    public void deleteShoppingCartItem(int id){
        shoppingCartDao.deleteById(id);
    }
}
