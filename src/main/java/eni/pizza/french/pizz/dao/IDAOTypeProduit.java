package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.TypeProduit;

import java.util.List;

public interface IDAOTypeProduit {
    List<TypeProduit> findAllTypes();

    TypeProduit findTypeById(Long id);
}
