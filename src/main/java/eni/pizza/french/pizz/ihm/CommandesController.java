package eni.pizza.french.pizz.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommandesController {
    @GetMapping ("panier")
    public String panier() {
        return "panier";
    }
}
