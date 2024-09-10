package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.TypeProduit;
import eni.pizza.french.pizz.dao.IDAOTypeProduit;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeProduitManager implements ITypeProduitManager {
    private static IDAOTypeProduit daoTypeProduit;

    public TypeProduitManager(IDAOTypeProduit daoTypeProduit) {
        this.daoTypeProduit = daoTypeProduit;
    }
    @Override
    public List<TypeProduit> getTypeProduits() {
        return daoTypeProduit.findAllTypes();
    }
    @Override
    public TypeProduit getTypeById(Long id) {
        return daoTypeProduit.findTypeById(id);
    }


}
