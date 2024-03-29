package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.interfaces.IDesertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DesertStoreController {

    @Autowired
    private IDesertService desertService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        List<Desert> allDeserts = desertService.getAllDeserts();
        modelAndView.addObject("deserts", allDeserts);
        modelAndView.setViewName("store");
        return modelAndView;
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/desert/{desertId}")
    public ModelAndView desertDetails(@PathVariable("desertId") int desertId){
        ModelAndView modelAndView = new ModelAndView();
        Desert desert = desertService.getDesertById(desertId);
        modelAndView.addObject("desert", desert);
        modelAndView.setViewName("desertDetails");
        return modelAndView;
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
