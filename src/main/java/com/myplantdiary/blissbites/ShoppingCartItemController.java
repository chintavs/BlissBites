package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartItemController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/items")
    public List<ShoppingCartItem> getAllItems() {
        return shoppingCartService.getAllShoppingCartItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity getItemById(@PathVariable("id") int id){
        ShoppingCartItem foundCartItem = shoppingCartService.getShoppingCartItem(id);
        if(foundCartItem != null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(foundCartItem, headers, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/add")
    public String addToCart(ShoppingCartItem item){
        shoppingCartService.addToShoppingCart(item);
        return "redirect:/cart";
    }

    @PostMapping("/addItem")
    public ResponseEntity addItemToCart(@RequestBody Desert desert, int quantity){
        ShoppingCartItem newCartItem = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            newCartItem.setDesert(desert);
            newCartItem.setQuantity(quantity);
            shoppingCartService.addToShoppingCart(newCartItem);
        } catch (Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newCartItem, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteItem(){
        shoppingCartService.deleteAll();
    }
}
