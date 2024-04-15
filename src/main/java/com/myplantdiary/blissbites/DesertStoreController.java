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

/**
 * This is the controller for Desert store REST endpoints and web UI.
 * </p>
 * Handles UI interactions with for the Desert Store web page
 */
@Controller
public class DesertStoreController {

    @Autowired
    private IDesertService desertService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    /**
     * Handles the base endpoint (/)
     *
     * @return desert store starter web page
     */
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

    /**
     * Handles the desert details view endpoint
     *
     * @param desertId the desert id
     * @return modal and HTML view web page for specified desert
     */
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

    /**
     * Handles the shopping cart view endpoint
     *
     * @param model the shopping cart list model
     * @return the shopping cart list html view
     */
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

    /**
     * Handles the Thank-you web page after checking out from cart
     * <p>
     * Changes stock count of desert item based on checked out quantity
     *
     * @return the string
     */
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
