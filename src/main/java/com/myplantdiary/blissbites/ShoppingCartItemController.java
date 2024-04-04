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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartItemController {

    @Autowired
    IShoppingCartService shoppingCartService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/items")
    public List<ShoppingCartItem> getAllItems() {
        return shoppingCartService.getAllShoppingCartItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity getItemById(@PathVariable("id") int id){
        Optional<ShoppingCartItem> foundCartItem = shoppingCartService.getShoppingCartItem(id);
        if(foundCartItem.isPresent()){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(foundCartItem, headers, HttpStatus.OK);
        }

        return null;
    }

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody Desert desert, int quantity){
        ShoppingCartItem newCartItem = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            newCartItem = shoppingCartService.addToShoppingCart(desert, quantity);
        } catch (Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newCartItem, headers, HttpStatus.OK);
    }

    /*@PutMapping("update/{id}")
    public void updateItem(@PathVariable("id") int id, @RequestBody ShoppingCartItem newItem){
        for(int i = 0; i < shoppingCart.size(); i++){
            ShoppingCartItem item = shoppingCart.get(i);
            if(item.getId() == id){
                shoppingCart.set(i, newItem);
                return;
            }
        }
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") int id){
        log.debug("Entering delete shoppingCartItem endpoint");
        try{
            shoppingCartService.deleteShoppingCartItem(id);
            log.info("ShoppingCartItem of ID:" + id + " was deleted.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            log.error("Unable to delete ShoppingCartItem of ID:" + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
