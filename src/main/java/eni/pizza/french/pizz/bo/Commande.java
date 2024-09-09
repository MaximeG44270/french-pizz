package eni.pizza.french.pizz.bo;

import org.springframework.format.number.money.MonetaryAmountFormatter;

import java.time.LocalDate;

public class Commande {
    private Long idCommande;
    private LocalDate dateHeureLivraison;
    private String Livraison;
    private MonetaryAmountFormatter prixTotal;
    private boolean estPayé;

    public Commande(Long idCommande, LocalDate dateHeureLivraison, String livraison, MonetaryAmountFormatter prixTotal, boolean estPayé) {
        this.idCommande = idCommande;
        this.dateHeureLivraison = dateHeureLivraison;
        Livraison = livraison;
        this.prixTotal = prixTotal;
        this.estPayé = estPayé;
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

    public MonetaryAmountFormatter getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(MonetaryAmountFormatter prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEstPayé() {
        return estPayé;
    }

    public void setEstPayé(boolean estPayé) {
        this.estPayé = estPayé;
    }
}
