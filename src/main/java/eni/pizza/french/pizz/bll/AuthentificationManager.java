package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Utilisateur;
import eni.pizza.french.pizz.dao.IDAOAuthentification;
import eni.pizza.french.pizz.dao.IDAORoleUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.List;

@Service
public class AuthentificationManager implements IAuthentificationManager {
   @Autowired
    IDAOAuthentification daOAuthentification;
   @Autowired
   IDAORoleUtilisateur daoRoleUtilisateur;
   @Override
   public Utilisateur getConnectedUtilisateurs(String email) {
        return daOAuthentification.getUtilisateurByEmail(email);
    }
    @Override
    public Utilisateur authenticate(String email, String password) {
       Utilisateur foundUtilisateur = daOAuthentification.getUtilisateurByEmailAndPassword(email, password);
       if (foundUtilisateur == null) {
           return null;
       }
       return foundUtilisateur;
    }
    @Override
    public List<Utilisateur> getConnectedUtilisateurs() {return daOAuthentification.getAllUtilisateurs();}
    @Override
    public Utilisateur getUtilisateurById(Long id) {
        Utilisateur utilisateur = daOAuthentification.getUtilisateurById(id);
        return utilisateur;
    }
    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateur.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(utilisateur.getPassword()));
        daOAuthentification.saveUtilisateur(utilisateur);
        daoRoleUtilisateur.saveRolebyId(utilisateur.getIdUtilisateur());
    }
    @Override
    public void deleteUser(Long id) {
       daOAuthentification.deleteUserById(id);
    }
}
