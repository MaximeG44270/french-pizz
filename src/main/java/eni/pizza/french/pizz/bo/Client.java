package eni.pizza.french.pizz.bo;

public class Client extends Utilisateur {
    private Commande panier;
    public Client(Commande panier, Long idUtilisateur, String nom, String prenom, String email, String password) {
        super(idUtilisateur, nom, prenom, email, password);
    }
    public Client()
    {
        super();
    }
    public Commande getPanier() {
        return panier;
    }
    public void setPanier(Commande panier) {
        this.panier = panier;
    }


}