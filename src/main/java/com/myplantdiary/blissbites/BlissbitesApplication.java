package com.myplantdiary.blissbites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BlissbitesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlissbitesApplication.class, args);
    }

    @Controller
    public static class HomeController {
        
        @GetMapping("/")
        public String home(Model model) {
            model.addAttribute("message", "Welcome to Bliss Bites!");
            return "index"; // returning index.html
        }

        @GetMapping("/store")
        public String store(Model model) {
            return "store"; // returning store.html
        }
    }
}
