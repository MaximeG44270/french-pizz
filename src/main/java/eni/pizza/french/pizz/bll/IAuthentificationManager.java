package eni.pizza.french.pizz.bll;

import eni.pizza.french.pizz.bo.Utilisateur;

import java.util.List;

public interface IAuthentificationManager {

    Utilisateur getConnectedUtilisateurs(String email);

    Utilisateur authenticate(String email, String password);

    List<Utilisateur> getConnectedUtilisateurs();

    Utilisateur getUtilisateurById(Long id);

    void saveUtilisateur(Utilisateur utilisateur);
}
