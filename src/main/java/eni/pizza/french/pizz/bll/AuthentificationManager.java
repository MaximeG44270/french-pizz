package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Utilisateur;
import eni.pizza.french.pizz.dao.IDAOAuthentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthentificationManager implements IAuthentificationManager {
   @Autowired
    IDAOAuthentification daOAuthentification;
    public Utilisateur getConnectedUtilisateurs(String email) {
        Utilisateur connectedUtilisateur = daOAuthentification.getUtilisateurByEmail(email);
        return connectedUtilisateur;
    }
}
