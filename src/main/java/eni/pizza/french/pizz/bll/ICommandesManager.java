package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Commande;

import java.util.List;

public interface ICommandesManager {
    List<Commande> getAllCommandesGerant();

    List<Commande> getAllCommandesPizzaolo();

    List<Commande> getAllCommandesLivreur();

    Commande getCommandebyId(Long id);

    void saveCommande(Commande commande);

    void deleteCommande(Long id);
}
