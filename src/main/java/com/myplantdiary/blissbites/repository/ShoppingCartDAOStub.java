package com.myplantdiary.blissbites.repository;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;

import java.util.*;

public class ShoppingCartDAOStub implements IShoppingCartDAO {

    Map<Integer, ShoppingCartItem> allCartItems = new HashMap<>();

    @Override
    public ShoppingCartItem save(ShoppingCartItem cartItem) {
        if (cartItem.getQuantity() <= cartItem.getDesert().getStockCount()) {
            allCartItems.put(cartItem.getId(), cartItem);
            return cartItem;
        }

        return null;
    }


    @Override
    public <S extends ShoppingCartItem> Iterable<S> saveAll(Iterable<S> entities) {
        for (ShoppingCartItem cartItem: entities) {
            if (cartItem.getQuantity() <= cartItem.getDesert().getStockCount()) {
                allCartItems.put(cartItem.getId(), cartItem);
            }
        }
        return (Iterable<S>) allCartItems.values().iterator();
    }

    @Override
    public Optional<ShoppingCartItem> findById(Integer id) {
        return Optional.ofNullable(allCartItems.get(id));
    }

    @Override
    public boolean existsById(Integer id) {
        return allCartItems.containsKey(id);
    }

    @Override
    public List<ShoppingCartItem> findAll() {
        return allCartItems.values().stream().toList();
    }

    @Override
    public List<ShoppingCartItem> findAllById(Iterable<Integer> ids) {
        List<ShoppingCartItem> cartItems = new ArrayList<>();

        for (int id: ids) {
            if (allCartItems.containsKey(id)) {
                cartItems.add(allCartItems.get(id));
            }
        }
        return cartItems;
    }

    @Override
    public long count() {
        return allCartItems.size();
    }

    @Override
    public void deleteById(Integer integer) {
        allCartItems.remove(integer);
    }

    @Override
    public void delete(ShoppingCartItem cartItem) {
        allCartItems.remove(cartItem.getId(), cartItem);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        for (int id: ids) {
            if (allCartItems.containsKey(id)) {
                allCartItems.remove(ids);
            }
        }
    }

    @Override
    public void deleteAll(Iterable<? extends ShoppingCartItem> cartItems) {
        for (ShoppingCartItem cartItem: cartItems) {
            if (allCartItems.containsValue(cartItem)) {
                allCartItems.remove(cartItem.getId(), cartItem);
            }
        }
    }

    @Override
    public void deleteAll() {
        allCartItems.clear();
    }
}
