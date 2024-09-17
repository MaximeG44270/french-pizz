package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.ProduitManager;
import eni.pizza.french.pizz.bll.TypeProduitManager;
import eni.pizza.french.pizz.bo.Produit;
import eni.pizza.french.pizz.bo.TypeProduit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class ProduitController {
    @Autowired
    private final ProduitManager produitManager;
    private final TypeProduitManager typeProduitManager;

    public ProduitController(ProduitManager produitManager, TypeProduitManager typeProduitManager) {
        this.produitManager = produitManager;
        this.typeProduitManager = typeProduitManager;
    }

    @GetMapping({"add-produit/{id}", "add-produit"})
    public String addProduit (@PathVariable(required = false) Long id, Model model, RedirectAttributes redirectAttributes) {

        Produit produit = new Produit();

        if(id != null){
            produit = produitManager.selectProduitById(id);
        }
        model.addAttribute("produit", produit);
        List<TypeProduit> typeProduits = typeProduitManager.getTypeProduits();
        model.addAttribute("types", typeProduits);
        //Afficher la page formulaire
        return "/add-produit-page";
    }

    /**
     * Traiter les données qui nous serons envoyées
     * @return
     * */

    @PostMapping("add-produit")
    public String processAddProduit(@Valid @ModelAttribute(name="produit") Produit produit, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        System.out.println("Produit reçu dans le Formulaire Front = " + produit);

        if (bindingResult.hasErrors()){
            System.out.println("Erreur de contrôle surface");
            return "/add-produit-page";
        }

        produitManager.saveProduit(produit);

        return "redirect:/add-produit-page";
    }
    @GetMapping("delete-produit/{id}")
    public String deleteProduit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Produit produit = produitManager.selectProduitById(id);
        produitManager.deleteProduitById(id);
        model.addAttribute("produit", produit);
        return "delete-produit-page";
    }
}
