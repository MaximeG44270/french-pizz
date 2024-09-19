package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.ProduitManager;
import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.DetailCommande;
import eni.pizza.french.pizz.bo.Produit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PanierController {

    private final ProduitManager produitManager;

    public PanierController(ProduitManager produitManager) {
        this.produitManager = produitManager;
    }

    @PostMapping("commander")
    public String commander(@RequestParam Long id, @RequestParam int quantity, Model model, RedirectAttributes redirectAttributes) {
        // Récupérer le panier de la session, ou en créer un nouveau si inexistant
        Commande panier = (Commande) model.getAttribute("panier");
        if (panier == null) {
            panier = new Commande();
            model.addAttribute("panier", panier);
        }

        Produit produit = produitManager.selectProduitById(id);

        // Créer un nouveau détail de commande (à adapter selon votre logique)
        DetailCommande dc = new DetailCommande(quantity, panier, produit);



        // Rediriger vers la page du menu
        return "redirect:/menu";
    }

    @GetMapping("panier")
    public String afficherPanier(Model model) {
        // Récupérer le panier de la session
        Commande panier = (Commande) model.addAttribute("panier");
        if (panier == null) {
            panier = new Commande();
            model.addAttribute("panier", panier);
        }
        model.addAttribute("panier", panier);

        return "/panier"; // Nom de la vue pour afficher le panier
    }
}
