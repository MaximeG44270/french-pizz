package eni.pizza.french.pizz.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Commande {
    private Long idCommande;
    private LocalDateTime dateHeureLivraison;
    private Utilisateur utilisateur;
    private int livraison;
    private EtatCommande etatCommande;
    private double prixTotal;
    private boolean estPayé;
    private List<DetailCommande> lignes;

    public Commande(Long idCommande, LocalDateTime dateHeureLivraison, Utilisateur utilisateur, int livraison, EtatCommande etatCommande, double prixTotal, boolean estPayé, List<DetailCommande> lignes) {
        this.idCommande = idCommande;
        this.dateHeureLivraison = dateHeureLivraison;
        this.utilisateur = utilisateur;
        this.livraison = livraison;
        this.etatCommande = etatCommande;
        this.prixTotal = prixTotal;
        this.estPayé = estPayé;
        this.lignes = lignes;
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

    public LocalDateTime getDateHeureLivraison() {
        return dateHeureLivraison;
    }

    public void setDateHeureLivraison(LocalDateTime dateHeureLivraison) {
        this.dateHeureLivraison = dateHeureLivraison;
    }

    public int getLivraison() {
        return livraison;
    }

    public void setLivraison(int livraison) {
        livraison = livraison;
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

    public List<DetailCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<DetailCommande> lignes) {
        this.lignes = lignes;
    }
}
