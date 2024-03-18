package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartItemController {
    public List<ShoppingCartItem> shoppingCart = new ArrayList<>();

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
        return null;
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody ShoppingCartItem item) {
        if (shoppingCart.contains(item)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Item already exists in cart
        } else {
            shoppingCart.add(item);
            return ResponseEntity.ok().build();
        }
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable("id") int id, @RequestBody ShoppingCartItem newItem) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            ShoppingCartItem item = shoppingCart.get(i);
            if (item.getId() == id) {
                shoppingCart.set(i, newItem);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") int id){
        shoppingCart.removeIf(item -> item.getId() == id);
    }
}
