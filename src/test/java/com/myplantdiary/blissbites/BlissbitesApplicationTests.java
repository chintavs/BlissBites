package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.repository.IShoppingCartDAO;
import com.myplantdiary.blissbites.repository.ShoppingCartDAOStub;
import com.myplantdiary.blissbites.service.implementation.ShoppingCartServiceStub;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class BlissbitesApplicationTests {
    
    private IShoppingCartService shoppingCartService;
    private IShoppingCartDAO shoppingCartDAO;
    private Desert dessert = new Desert();

    @Test
    void addDessertToShoppingCart() {
        givenDessertIsAvailable();
        whenUserAddsDessertToShoppingCart();
        thenDessertIsInCart();
    }

    private void givenDessertIsAvailable() {
        dessert.setId(1);
        dessert.setName("Chocolate Cake");
        dessert.setStockCount(10);
        dessert.setType("Cake");
        dessert.setDescription("Delicious Chocolate Cake made with Hershey's");
        dessert.setCost(9.99);
    }

    private void whenUserAddsDessertToShoppingCart() {
        shoppingCartDAO = new ShoppingCartDAOStub();
        shoppingCartService = new ShoppingCartServiceStub(shoppingCartDAO);

        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setId(dessert.getId());
        cartItem.setQuantity(1);
        cartItem.setDesert(dessert);
        shoppingCartService.addToShoppingCart(cartItem);
    }

    private void thenDessertIsInCart() {
        ShoppingCartItem cartItem = shoppingCartService.getShoppingCartItem(dessert.getId());
        assertEquals(dessert, cartItem.getDesert());
    }

    @Test
    void preventOutOfStockItemsInShoppingCart() {
        givenItemIsOutOfStock();
        whenUserAddsDessertToShoppingCart();
        thenDessertIsNotInCart();
    }

    private void givenItemIsOutOfStock() {
        dessert.setId(1);
        dessert.setName("Chocolate Chip Cookie");
        dessert.setStockCount(0);
        dessert.setType("Cookie");
        dessert.setDescription("Delicious and Fresh Chocolate Chip Cookie");
        dessert.setCost(1.99);
    }

    private void thenDessertIsNotInCart() {
        ShoppingCartItem cartItem = shoppingCartService.getShoppingCartItem(dessert.getId());
        assertNull(cartItem);
    }
}
