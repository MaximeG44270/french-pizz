package eni.pizza.french.pizz.bo;

public class Produit {
    private Long id_produit;
    private String nom;
    private String description;
    private double prix;
    private String image_url;
    private int libelle;
    private TypeProduit typeProduit;
    public Produit() {
        super();
    }

    public Produit(Long id_produit, String nom, String description, double prix, String image_url, int libelle, TypeProduit typeProduit) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image_url = image_url;
        this.libelle = libelle;
        this.typeProduit = typeProduit;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }
    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    public int getLibelle() {
        return libelle;
    }

    public void setLibelle(int libelle) {
        this.libelle = libelle;
    }
}
