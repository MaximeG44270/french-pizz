package eni.pizza.french.pizz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    //test
    @GetMapping("")
    public String homePage() {
        return "home";
    }
}
