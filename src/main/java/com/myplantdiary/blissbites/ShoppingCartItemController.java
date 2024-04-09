package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartItemController {
    public List<ShoppingCartItem> shoppingCart = new ArrayList<>();
    @Autowired
    private IShoppingCartService shoppingCartService;

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
    public String addToCart(ShoppingCartItem item){
        shoppingCartService.addToShoppingCart(item);
        return "redirect:/cart";
    }

    @PutMapping("update/{id}")
    public void updateItem(@PathVariable("id") int id, @RequestBody ShoppingCartItem newItem){
        for(int i = 0; i < shoppingCart.size(); i++){
            ShoppingCartItem item = shoppingCart.get(i);
            if(item.getId() == id){
                shoppingCart.set(i, newItem);
                return;
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") int id){
        shoppingCart.removeIf(item -> item.getId() == id);
    }
}
