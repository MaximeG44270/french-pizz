package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.dao.IDAODetailsCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailCommandeManager implements IDetailCommandeManager {
    @Autowired
    private IDAODetailsCommande daodetailcommande;
}
