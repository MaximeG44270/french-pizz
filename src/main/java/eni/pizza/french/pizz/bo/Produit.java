package eni.pizza.french.pizz.bo;

public class Produit {
    private Long idProduit;
    private String nom;
    private String description;
    private double prix;
    private String image_url;
    private int libelle;
    private TypeProduit typeProduit;
    public Produit() {
        super();
    }

    public Produit(Long idProduit, String nom, String description, double prix, String image_url, int libelle, TypeProduit typeProduit) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image_url = image_url;
        this.libelle = libelle;
        this.typeProduit = typeProduit;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
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
