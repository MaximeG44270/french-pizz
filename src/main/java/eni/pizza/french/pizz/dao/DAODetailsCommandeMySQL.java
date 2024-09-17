package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DAODetailsCommandeMySQL implements IDAODetailsCommande {
    @Autowired
    JdbcTemplate jdbcTemplate;
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
}
