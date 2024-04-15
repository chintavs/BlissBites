package com.myplantdiary.blissbites;

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

import java.util.List;
/**
 * This is the controller for Shopping Cart REST endpoints and web UI.
 * </p>
 * It handles CRUD operations for the Shopping Cart list.
 * </p>
 * Handles UI interactions with Shopping Cart web page
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartItemController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/items")
    public ResponseEntity getAllItems() {
        List<ShoppingCartItem> cartItems = shoppingCartService.getAllShoppingCartItems();
        if(!cartItems.isEmpty()){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(cartItems, headers, HttpStatus.OK);
        }
        return null;
    }

    /**
     * Gets cart item by given ID
     *
     * @param id the ID of the cart item in the list
     *
     * @return Status Code 200: Cart item found
     */
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

    /**
     * Adds desert item to shopping cart
     *
     * @param cartItem the cart item that contains the selected desert
     *
     * @return Status Code 200: Item was successfully added to shopping cart
     *         Status Code 500: Unable to add item to shopping cart
     */
    @PostMapping("/addItem")
    public ResponseEntity addItemToCart(@RequestBody ShoppingCartItem cartItem){
        ShoppingCartItem newCartItem = new ShoppingCartItem();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            newCartItem.setDesert(cartItem.getDesert());
            newCartItem.setQuantity(cartItem.getQuantity());
            shoppingCartService.addToShoppingCart(newCartItem);
        } catch (Exception e){
            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newCartItem, headers, HttpStatus.OK);
    }

    /**
     *  Removes all items from the shopping cart
     *
     * @return Status Code 200: Shopping Cart was successfully cleared
     *         Status Code 500: Unable to clear shopping cart
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteItem(){
        try{
            shoppingCartService.deleteAll();
            log.info("Successfully deleted all shopping cart items.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            log.error("Unable to delete shopping cart items. Message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
