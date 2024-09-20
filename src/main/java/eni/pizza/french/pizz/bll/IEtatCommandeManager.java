package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.EtatCommande;

public interface IEtatCommandeManager {
    EtatCommande getEtatById(Long id);
}
