package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cart")
public class ShoppingCartItemController {
    public List<ShoppingCartItem> shoppingCart = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartItemController.class.getName());


    @GetMapping("/items")
    public List<ShoppingCartItem> getAllItems() {
        return shoppingCart;
    }

    @GetMapping("/items/{id}")
    public ShoppingCartItem getItemById(@PathVariable("id") int id){
        for(ShoppingCartItem item: shoppingCart){
            if(item.getId() == id){
                return item;
            }
        }
        LOGGER.warning("Item with ID " + id + " not found");
        return null;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody ShoppingCartItem item){
        if (item == null) {
            LOGGER.severe("No item provided in request body");
            return;
        }
        shoppingCart.add(item);
    }

    @PutMapping("update/{id}")
    public void updateItem(@PathVariable("id") int id, @RequestBody ShoppingCartItem newItem){
        if (newItem == null) {
            LOGGER.severe("No item provided in request body for updateItem");
            return;
        }
        for(int i = 0; i < shoppingCart.size(); i++){
            ShoppingCartItem item = shoppingCart.get(i);
            if(item.getId() == id){
                shoppingCart.set(i, newItem);
                return;
            }
        }
        LOGGER.warning("Item with ID " + id + " not found for update");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") int id){
        boolean itemRemoved = false;
        for (ShoppingCartItem item : shoppingCart) {
            if (item.getId() == id) {
                shoppingCart.remove(item);
                itemRemoved = true;
                break;
            }
        }
        if (!itemRemoved) {
            LOGGER.warning("Item with ID " + id + " not found for delete");
        }
//        shoppingCart.removeIf(item -> item.getId() == id);
    }
}
