package eni.pizza.french.pizz.ihm;

import eni.pizza.french.pizz.bll.ProduitManager;
import eni.pizza.french.pizz.bll.TypeProduitManager;
import eni.pizza.french.pizz.bo.Produit;
import eni.pizza.french.pizz.bo.TypeProduit;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class ProduitController {
    private final ProduitManager produitManager;
    private final TypeProduitManager typeProduitManager;

    public ProduitController(ProduitManager produitManager, TypeProduitManager typeProduitManager) {
        this.produitManager = produitManager;
        this.typeProduitManager = typeProduitManager;
    }

    @GetMapping({"add-produit/{id}", "add-produit"})
    public String addProduit (@PathVariable(required = false) Long id, Model model, RedirectAttributes redirectAttributes) {

        //Préparer ce que tu vas envoyer dans le formulaire par défaut
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
    //Postmapping obligatoire
    @PostMapping("add-produit")
    public String processAddProduit(@Valid @ModelAttribute(name="produit") Produit produit, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        System.out.println("Produit reçu dans le Formulaire Front = " + produit);

        //Objectif tester la validité des data par contrôle de surface (=check du format uniquement)
        if (bindingResult.hasErrors()){
            System.out.println("Erreur de contrôle surface");
            //PAS DE REDIRECTION SI ERREUR FORMULAIRE
            //Car redirection avec redirect signifie nouvelle url et donc nouvelle requête et donc perte des erreurs stockées
            return "/add-produit-page";
        }


        //On sauvegarde le movie dans la BDD db_movie en faisant appel à la couche BLL via MovieManager
        produitManager.saveProduit(produit);

        //Ajouter un message temporaire (flash card) dans la section "flashMessage"
        /*EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous vous êtes connecté-e avec succès.");*/
        //Afficher la page Accueil
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
