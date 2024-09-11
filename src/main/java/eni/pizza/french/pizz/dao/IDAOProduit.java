package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Produit;

import java.util.List;

public interface IDAOProduit {

    List<Produit> findAllProduit();

    Produit selectProduitById(Long id);

    void saveProduit(Produit produit);

    void deleteProduitById(Long id);
}
