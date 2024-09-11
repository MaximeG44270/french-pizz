package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Produit;
import eni.pizza.french.pizz.dao.IDAOProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduitManager {
    @Autowired
    private IDAOProduit daoProduit;

    /**
     * Manager qui récupère et retourne la liste des Produit
     * @return
     */
    public List<Produit> getAllProduits(){
        return daoProduit.findAllProduit();
    }

    public Produit selectProduitById(Long id){
        //Récupérer le produit via la DAO
        Produit produit = daoProduit.selectProduitById(id);
        return produit;
    }

    /**
     * Appelera la DAO pour sauvegarder un Produit produit
     * @param produit
     */

    public void saveProduit(Produit produit){
        daoProduit.saveProduit(produit);
    }
    public void deleteProduitById(Long id){
        daoProduit.deleteProduitById(id);
    }
}
