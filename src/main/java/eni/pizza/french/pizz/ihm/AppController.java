package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.ICommandesManager;
import eni.pizza.french.pizz.bll.ProduitManager;
import eni.pizza.french.pizz.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    ProduitManager produitManager;

    @Autowired
    ICommandesManager commandesManager;

    public AppController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @GetMapping({"home","/",""})
    public String homePage() {
        return "home";
    }

    @GetMapping("menu")
    public String menuPage(Model model) {
        List<Produit> produits = produitManager.getAllProduits();

        model.addAttribute("produits", produits);

        return "menu";
    }

    @GetMapping("admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("delivery")
    public String deliveryPage(Model model, RedirectAttributes redirectAttributes) {

        return "delivery";
    }
    @GetMapping("profil")
    public String profil(Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        model.addAttribute("email",email);
        model.addAttribute("authorities",authorities);
        return "profil";
    }

    @GetMapping("nos-pizzas")
    public String nosPizzas(Model model) {
        return "nos-pizzas";
    }
}


