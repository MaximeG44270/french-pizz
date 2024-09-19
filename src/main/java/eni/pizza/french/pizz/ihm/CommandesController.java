package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.ICommandesManager;
import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.DetailCommande;
import eni.pizza.french.pizz.dao.IDAOCommandes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommandesController
{
    List<DetailCommande> dc = new ArrayList<DetailCommande>();
    @Autowired
    ICommandesManager commandesManager;

    @GetMapping ("panier")
    public String panier() {
        return "panier";
    }


    @GetMapping("list-commandes")
    public String viewCommandes(Model model, RedirectAttributes redirectAttributes)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().toString();

        if (authorities.contains("[Gérant]"))
        {
            List<Commande> commandes = commandesManager.getAllCommandesGerant();
            model.addAttribute("commandes", commandes); }
        else if (authorities.contains("[Pizzaolo]"))
            {model.addAttribute("commandes", commandesManager.getAllCommandesPizzaolo());}
        else if (authorities.contains("[Livreur]"))
            {model.addAttribute("commandes", commandesManager.getAllCommandesLivreur());}
        else System.out.println("Vous ne pouvez pas y accéder");
        return "delivery";
    }
    @GetMapping("choix/{id}")
    public String panier(Long id, Model model, RedirectAttributes redirectAttributes)
    {
        DetailCommande dc = new DetailCommande();


        return "redirect:/menu";
    }
}
