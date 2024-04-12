package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import com.myplantdiary.blissbites.service.interfaces.IDesertService;
import com.myplantdiary.blissbites.service.interfaces.IShoppingCartService;
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

    @Autowired
    private IShoppingCartService shoppingCartService;

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
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setDesert(desert);
        modelAndView.addObject("desert", desert);
        modelAndView.addObject("shoppingCartItem", shoppingCartItem);
        modelAndView.setViewName("desertDetails");
        return modelAndView;
    }

    @RequestMapping("/cart")
    public String cart(Model model) {
        List<ShoppingCartItem> cartItems = new ArrayList<>();
        double total = 0.00;

        List<ShoppingCartItem> allShoppingCartItems = shoppingCartService.getAllShoppingCartItems();

        for (ShoppingCartItem cartItem : allShoppingCartItems) {
            cartItems.add(shoppingCartService.getShoppingCartItem(cartItem.getId()));
            total += cartItem.getDesert().getCost() * cartItem.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", String.format("%.2f", total));

        return "cart";
    }
    @RequestMapping("/thankYou")
    public String thankYou() {
        List<ShoppingCartItem> cartItems = shoppingCartService.getAllShoppingCartItems();
        for(ShoppingCartItem item : cartItems){
            Desert desert = desertService.getDesertById(item.getDesert().getId());
            desert.setStockCount(desert.getStockCount() - item.getQuantity());
            desertService.save(desert);
        }
        shoppingCartService.deleteAll();
        return "thankYou";
    }
}
