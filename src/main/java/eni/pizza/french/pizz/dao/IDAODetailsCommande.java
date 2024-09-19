package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.DetailCommande;

import java.util.List;

public interface IDAODetailsCommande {
    List<DetailCommande> selectDetailCommandeById(Long id);

    void saveDetailCommande(DetailCommande detailCommande);
}
