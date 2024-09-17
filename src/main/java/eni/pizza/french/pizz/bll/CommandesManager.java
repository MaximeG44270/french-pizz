package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.dao.IDAOCommandes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandesManager implements ICommandesManager {
    @Autowired
    IDAOCommandes daoCommandes;
    @Override
    public List<Commande> getAllCommandesGerant(){
        return daoCommandes.findAllCommandesGerant();
    }
    @Override
    public List<Commande> getAllCommandesPizzaolo(){
        return daoCommandes.findAllCommandesPizzaolo();
    }
    @Override
    public List<Commande> getAllCommandesLivreur(){
        return daoCommandes.findAllCommandesLivreur();
    }
    @Override
    public Commande getCommandebyId(Long id){
        return daoCommandes.selectCommandeById(id);
    }
    @Override
    public void saveCommande(Commande commande){
        daoCommandes.saveCommande(commande);
    }
    @Override
    public void deleteCommande(Long id){
        daoCommandes.deleteCommandeById(id);
    }

}
