package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Utilisateur;

import java.util.List;

public interface IDAOAuthentification {
    List<Utilisateur> getAllUtilisateurs();

    Utilisateur getUtilisateurById(long id);

    Utilisateur getUtilisateurByEmailAndPassword(String email, String password);

    Utilisateur getUtilisateurByEmail(String email);

    void saveUtilisateur(Utilisateur utilisateur);

    void deleteUserById(Long id);
}
