package eni.pizza.french.pizz.bo;

public class DetailCommande {
    private int quantity;

    public DetailCommande(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
