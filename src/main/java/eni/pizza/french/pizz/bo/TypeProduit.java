package eni.pizza.french.pizz.bo;

public class TypeProduit {
    private Long idTypeProduit;
    private String libelle;

    public TypeProduit() {
        super();
    }

    public TypeProduit(Long idTypeProduit) {
        this.idTypeProduit = idTypeProduit;
    }


    public TypeProduit(Long idTypeProduit, String libelle) {
        this.idTypeProduit = idTypeProduit;
        this.libelle = libelle;
    }

    public Long getIdTypeProduit() {
        return idTypeProduit;
    }

    public void setIdTypeProduit(Long idTypeProduit) {
        this.idTypeProduit = idTypeProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
