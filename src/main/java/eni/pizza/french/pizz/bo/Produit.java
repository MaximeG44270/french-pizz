package eni.pizza.french.pizz.bo;

import org.springframework.format.number.money.MonetaryAmountFormatter;

public class Produit {
    private String nom;
    private Long idProduit;
    private String description;
    private MonetaryAmountFormatter prix;
    private String image_url;

    public Produit(String nom, Long idProduit, String description, MonetaryAmountFormatter prix, String image_url) {
        this.nom = nom;
        this.idProduit = idProduit;
        this.description = description;
        this.prix = prix;
        this.image_url = image_url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MonetaryAmountFormatter getPrix() {
        return prix;
    }

    public void setPrix(MonetaryAmountFormatter prix) {
        this.prix = prix;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
