package eni.pizza.french.pizz.bo;

import org.springframework.format.number.money.MonetaryAmountFormatter;

public abstract class Produit {
    private Long id_produit;
    private String nom;
    private String description;
    private MonetaryAmountFormatter prix;
    private String image_url;
    public Produit() {
        super();
    }

    public Produit(Long id_produit, String nom, String description, MonetaryAmountFormatter prix, String image_url) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image_url = image_url;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
