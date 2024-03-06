package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.implementation.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BlissbitesApplicationTests {
    
    private ShoppingCartService shoppingCartService = new ShoppingCartService();
    private Desert dessert1 = new Desert();
    private Desert dessert2 = new Desert();

    @Test
    void addDessertToShoppingCart() {
        givenDessertDataAreAvailable();
        whenUserAddsDessertToShoppingCart();
        thenDessertIsInCart();
    }

    private void givenDessertDataAreAvailable() {
        dessert1.setId(1);
        dessert1.setName("Chocolate Cake");
        dessert1.setStockCount(10);
        dessert1.setType("Cake");
        dessert1.setDescription("Delicious Chocolate Cake made with Hershey's");
        dessert1.setCost(9.99);
    }

    private void whenUserAddsDessertToShoppingCart() {
        shoppingCartService.addToShoppingCart(dessert1, 1);
    }

    private void thenDessertIsInCart() {
        ShoppingCartItem cartItem = shoppingCartService.getShoppingCartItem(dessert1);
        assertEquals(dessert1, cartItem.getDesert());
    }

    @Test
    void preventOutOfStockItemsInShoppingCart() {
        givenItemIsOutOfStock();
        whenUserAddsUnavailableDessertToShoppingCart();
        thenDessertIsNotInCart();
    }

    private void givenItemIsOutOfStock() {
        dessert2.setId(1);
        dessert2.setName("Chocolate Chip Cookie");
        dessert2.setStockCount(0);
        dessert2.setType("Cookie");
        dessert2.setDescription("Delicious and Fresh Chocolate Chip Cookie");
        dessert2.setCost(1.99);
    }

    private void whenUserAddsUnavailableDessertToShoppingCart() {
        shoppingCartService.addToShoppingCart(dessert2, 3);
    }

    private void thenDessertIsNotInCart() {
        ShoppingCartItem cartItem = shoppingCartService.getShoppingCartItem(dessert2);
        assertNull(cartItem);
    }
}
