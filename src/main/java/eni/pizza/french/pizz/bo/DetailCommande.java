package eni.pizza.french.pizz.bo;

public class DetailCommande {
    private int quantity;
    private Commande commande;
    private Produit produit;

    public DetailCommande(Produit produit, Commande commande, int quantity) {
        this.produit = produit;
        this.commande = commande;
        this.quantity = quantity;
    }
    public DetailCommande(){
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
