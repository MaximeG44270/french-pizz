package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Produit;
import eni.pizza.french.pizz.bo.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class DAOProduitMySQL implements IDAOProduit {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Le code qui permet de savoir comment relier/convertir (mapper) un résultat SQL en objet Java
     * autrement dit comment récupérer un movie de la table movie de notre BDD MySql db_movie
     * et l'injecter sous forme d'instance de class Movie sous java
     */

    static final RowMapper<Produit> PRODUIT_ROW_MAPPER = new RowMapper<Produit>() {

        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit();

            produit.setIdProduit(rs.getLong("id_produit"));
            produit.setNom(rs.getString("nom"));
            produit.setDescription(rs.getString("description"));
            produit.setPrix(rs.getDouble("prix"));
            produit.setImage_url(rs.getString("image_url"));
            TypeProduit typeProduit = new TypeProduit();
            typeProduit.setIdTypeProduit(rs.getLong("TYPE_PRODUIT_id_type_produit"));
            produit.setTypeProduit(typeProduit);

            return produit;
        }
    };

    @Override
    public List<Produit> findAllProduit() {

        return jdbcTemplate.query("SELECT * FROM produit", PRODUIT_ROW_MAPPER);
    }

    @Override
    public Produit selectProduitById(Long id) {

        List<Produit> produits = jdbcTemplate.query("SELECT * FROM produit WHERE id_produit = ?", PRODUIT_ROW_MAPPER, id);

        if (produits.size() == 0) {
            return null;
        }

        return produits.get(0);
    }

    @Override
    public void saveProduit(Produit produit) {
        String sql;

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_id_produit", produit.getIdProduit());
        mapSqlParameterSource.addValue("new_nom", produit.getNom());
        mapSqlParameterSource.addValue("new_description", produit.getDescription());
        mapSqlParameterSource.addValue("new_prix", produit.getPrix());
        mapSqlParameterSource.addValue("new_image_url", produit.getImage_url());
        mapSqlParameterSource.addValue("new_id_type_produit", produit.getTypeProduit().getIdTypeProduit());

        if(selectProduitById(produit.getIdProduit()) != null){
            sql= "UPDATE produit SET nom = :new_nom, description = :new_description, prix = :new_prix, image_url = :new_image_url, id_type_produit = new_id_type_produit:  WHERE id_produit = :new_id_produit";
        } else {
            sql = "INSERT INTO produit (id_produit, nom, description, prix, image_url, TYPE_PRODUIT_id_type_produit) VALUES (:new_id_produit, :new_nom, :new_description, :new_prix, :new_image_url, :new_id_type_produit)";
        }

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
        System.out.println("Produit inséré en table produit de BDD db_pizza : " +produit);

    }
    @Override
    public void deleteProduitById(Long id)
    {
        int nbSuppression = jdbcTemplate.update("DELETE produit FROM produit WHERE id_produit = ?", id);
        System.out.println("Produit supprimé");
    }
}
