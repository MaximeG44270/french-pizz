package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.*;
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
public class DAODetailsCommandeMySQL implements IDAODetailsCommande {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final RowMapper<DetailCommande> DETAIL_COMMANDE_ROW_MAPPER = new RowMapper<DetailCommande>() {
        public DetailCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetailCommande detailCommande = new DetailCommande();
            detailCommande.setQuantity(rs.getInt("quantite"));
            Commande commande = new Commande();
            commande.setIdCommande(rs.getLong("COMMANDE_id_commande"));
            detailCommande.setCommande(commande);
            Produit produit = new Produit();
            produit.setIdProduit(rs.getLong("PRODUIT_id_produit"));
            detailCommande.setProduit(produit);
            return detailCommande;
        }
    };
    @Override
    public List<DetailCommande> selectDetailCommandeById(Long id) {

        List<DetailCommande> detailCommandes = jdbcTemplate.query("SELECT * FROM detail_commande WHERE COMMANDE_id_commande = ?", DETAIL_COMMANDE_ROW_MAPPER, id);

        if (detailCommandes.size() == 0) {
            return null;
        }

        return detailCommandes;
    }
    @Override
    public void saveDetailCommande(DetailCommande detailCommande) {
        String sql;

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_quantite", detailCommande.getQuantity());
        mapSqlParameterSource.addValue("new_id_commande", detailCommande.getCommande().getIdCommande());
        mapSqlParameterSource.addValue("new_id_produit", detailCommande.getProduit().getIdProduit());

        sql = "INSERT INTO detail_commande (quantite, COMMANDE_id_commande, Produit_id_produit) VALUES (:new_quantite, :new_id_commande, :new_id_produit)";
        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);

    }
    @Override
    public void supprimerDetailCommande(Long id_produit, Long id_commande) {
        int nbSuppression = jdbcTemplate.update("DELETE detail_commande FROM detail_commande WHERE PRODUIT_id_produit = ? && COMMANDE_id_commande = ?", id_produit, id_commande);
        System.out.println("Ligne supprimée");
    }

}
