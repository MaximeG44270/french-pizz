package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.DetailCommande;
import eni.pizza.french.pizz.dao.IDAODetailsCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailCommandeManager implements IDetailCommandeManager {
    @Autowired
    private IDAODetailsCommande daodetailcommande;

    @Override
    public void save(DetailCommande detailCommande) {
        daodetailcommande.saveDetailCommande(detailCommande);
    }
}
