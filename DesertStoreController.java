package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DesertStoreController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Bliss Bites!");
        return "index"; // returning index.html
    }

    @GetMapping("/store")
    public String store(Model model) {
        return "store"; // returning store.html
    }

    @RequestMapping("/desert/{desertId}")
    public String desertDetails(@PathVariable("desertId") int desertId, Model model){
        //TODO fetch desert from database
        Desert desert = new Desert();
        desert.setName("Cake " + desertId);
        desert.setDescription("This is the description for desert number " + desertId);
        desert.setCost(24.99);
        model.addAttribute("desert", desert);
        return "desertDetails";
    }

    @RequestMapping("/cart")
    public String cart(Model model) {
        List<ShoppingCartItem> cartItems = new ArrayList<>();
        double total = 0.00;

        //TODO fetch cart from database
        Desert desert1 = new Desert();
        desert1.setName("Cake 4");
        desert1.setCost(12.99);

        Desert desert2 = new Desert();
        desert2.setName("Cookie 1");
        desert2.setCost(2.50);

        ShoppingCartItem item1 = new ShoppingCartItem();
        item1.setDesert(desert1);
        item1.setQuantity(4);

        ShoppingCartItem item2 = new ShoppingCartItem();
        item2.setDesert(desert2);
        item2.setQuantity(2);

        cartItems.add(item1);
        cartItems.add(item2);

        for (ShoppingCartItem cartItem : cartItems) {
            total += cartItem.getDesert().getCost() * cartItem.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart";
    }
}
