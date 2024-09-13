package eni.pizza.french.pizz.bo;

public class Client extends Utilisateur {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
<<<<<<< Updated upstream
    private String email;
    private String password;
    private Commande panier;

}
=======
    private String rue;
    private String ville;
    private int codePostal;
    private Commande panier;

    public Client(Long idClient, String nom, String prenom, String rue, String ville, int codePostal, Commande panier) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.panier = panier;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public Commande getPanier() {
        return panier;
    }
    public void setPanier(Commande panier) {
        this.panier = panier;
    }
}
>>>>>>> Stashed changes
