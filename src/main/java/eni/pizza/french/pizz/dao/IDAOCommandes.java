package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.Produit;

import java.util.List;

public interface IDAOCommandes {

    List<Commande> findAllCommandesPizzaolo();

    List<Commande> findAllCommandesGerant();

    List<Commande> findAllCommandesLivreur();

    Commande selectCommandeById(Long id);

    void saveCommande(Commande commande);

    void deleteCommandeById(Long id);
}
