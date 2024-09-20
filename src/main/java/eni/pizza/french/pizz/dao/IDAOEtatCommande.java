package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.EtatCommande;

import java.util.List;

public interface IDAOEtatCommande {
    EtatCommande selectEtatCommandeById(Long id);
}
