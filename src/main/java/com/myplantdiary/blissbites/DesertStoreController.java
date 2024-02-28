package com.myplantdiary.blissbites;

import com.myplantdiary.blissbites.dto.Desert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DesertStoreController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/desert/{desertId}")
    public String desertDetails(@PathVariable("desertId") int desertId, Model model){
        //TODO fetch desert from database
        Desert desert = new Desert();
        desert.setName("Cake " + desertId);
        desert.setDescription("This is the description for desert number " + desertId);
        desert.setCost(24.99);
        model.addAttribute(desert);
        return "desertDetails";
    }
}
