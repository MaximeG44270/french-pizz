package eni.pizza.french.pizz.bo;

import java.time.LocalDate;
import java.util.List;

public class Commande {
    private Long idCommande;
    private LocalDate dateHeureLivraison;
    private Client client;
    private String Livraison;
    private EtatCommande etatCommande;
    private Utilisateur utilisateur;
    private double prixTotal;
    private boolean estPayé;
    private List<DetailCommande> lignes;

    public Commande(Long idCommande, LocalDate dateHeureLivraison, Client client, String livraison, EtatCommande etatCommande, Utilisateur utilisateur, double prixTotal, boolean estPayé) {
        this.idCommande = idCommande;
        this.dateHeureLivraison = dateHeureLivraison;
        this.client = client;
        Livraison = livraison;
        this.etatCommande = etatCommande;
        this.utilisateur = utilisateur;
        this.prixTotal = prixTotal;
        this.estPayé = estPayé;
    }
    public Commande ()
    {
        super();
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public LocalDate getDateHeureLivraison() {
        return dateHeureLivraison;
    }

    public void setDateHeureLivraison(LocalDate dateHeureLivraison) {
        this.dateHeureLivraison = dateHeureLivraison;
    }

    public String getLivraison() {
        return Livraison;
    }

    public void setLivraison(String livraison) {
        Livraison = livraison;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEstPayé() {
        return estPayé;
    }

    public void setEstPayé(boolean estPayé) {
        this.estPayé = estPayé;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
