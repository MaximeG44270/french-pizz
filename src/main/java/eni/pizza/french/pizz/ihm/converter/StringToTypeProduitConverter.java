package eni.pizza.french.pizz.ihm.converter;

import eni.pizza.french.pizz.bll.ITypeProduitManager;
import eni.pizza.french.pizz.bo.TypeProduit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTypeProduitConverter implements Converter<String, TypeProduit> {
    private ITypeProduitManager typeProduitManager;
    public StringToTypeProduitConverter(ITypeProduitManager typeProduitManager) {
        this.typeProduitManager = typeProduitManager;
    }
    @Override
    public TypeProduit convert(String typeProduit) {
        TypeProduit letypeProduit = typeProduitManager.getTypeById(Long.parseLong(typeProduit));

        return letypeProduit;
    }
}
