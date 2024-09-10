package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.TypeProduit;

import java.util.List;

public interface ITypeProduitManager {
    List<TypeProduit> getTypeProduits();

    TypeProduit getTypeById(Long id);
}
