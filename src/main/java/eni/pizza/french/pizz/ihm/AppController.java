package eni.pizza.french.pizz.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    //test
    @GetMapping("home")
    public String homePage() {
        return "home";
    }

    @GetMapping("menu")
    public String menuPage() {
        return "menu";
    }
}
