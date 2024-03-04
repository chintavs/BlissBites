package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.implementation.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BlissbitesApplicationTests {
    
    private ShoppingCartService shoppingCartService;
    private Desert dessert = new Desert();


    @Test
    void contextLoads() {
    }

    @Test
    void addDessertToShoppingCart() {
        givenDessertDataAreAvailable();
        whenUserAddsDessertToShoppingCart();
        thenDessertIsInCart();
    }

    private void givenDessertDataAreAvailable() {
        shoppingCartService = new ShoppingCartService();
    }

    private void whenUserAddsDessertToShoppingCart() {
        dessert.setId(1);
        dessert.setName("Chocolate Cake");
        dessert.setType("Cake");
        dessert.setDescription("Delicious Chocolate Cake made with Hershey's");
        dessert.setCost(9.99);
        shoppingCartService.addToShoppingCart(dessert, 1);
    }

    private void thenDessertIsInCart() {
        ShoppingCartItem cartItem = shoppingCartService.getShoppingCartItem(dessert);
        assertEquals(dessert, cartItem.getDesert());
    }
}
