package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.EtatCommande;
import eni.pizza.french.pizz.dao.IDAOEtatCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtatCommandeManager implements IEtatCommandeManager {
    @Autowired
    private IDAOEtatCommande daoEtatCommande;
    @Override
    public EtatCommande getEtatById(Long id){
        return daoEtatCommande.selectEtatCommandeById(id);
    }

}
