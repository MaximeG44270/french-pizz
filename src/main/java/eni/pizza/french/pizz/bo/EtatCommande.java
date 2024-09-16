package eni.pizza.french.pizz.bo;

public class EtatCommande {
    private Long idEtat;
    private String libelle;

    public EtatCommande(String libelle, Long idEtat) {
        this.libelle = libelle;
        this.idEtat = idEtat;
    }
    public EtatCommande (){
        super();
    }

    public Long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
