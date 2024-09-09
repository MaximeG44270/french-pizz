package eni.pizza.french.pizz.bo;

import org.springframework.format.number.money.MonetaryAmountFormatter;

public class Pizza extends Produit {
    public Pizza(Long id_produit, String nom, String description, MonetaryAmountFormatter prix, String image_url) {
        super(id_produit, nom, description, prix, image_url);
    }
   public Pizza ()
   {
       super();
   }
}
