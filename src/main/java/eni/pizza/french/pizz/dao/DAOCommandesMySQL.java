package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.TypeProduit;
import eni.pizza.french.pizz.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class DAOCommandesMySQL {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static final RowMapper<Commande> COMMANDE_ROW_MAPPER = new RowMapper<Commande>() {
        public Commande mapRow(ResultSet rs, int rowNum) throws SQLException {
            Commande commande = new Commande();
            commande.setIdCommande(rs.getLong("id_commande"));
            commande.setDateHeureLivraison(rs.getTimestamp("date_heure_livraison").toLocalDateTime());
//            commande.setPrenom(rs.getString("CLIENT_id_client"));
//            commande.setEmail(rs.getString("livraison"));
//            commande.setEmail(rs.getString("ETAT_id_etat"));
//            commande.setEmail(rs.getString("UTILISATEUR_id_utilisateur"));
//            commande.setEmail(rs.getString("prix_total"));
//            commande.setEmail(rs.getString("est_paye"));
//            TypeProduit typeProduit = new TypeProduit();
//            typeProduit.setIdTypeProduit(rs.getLong("TYPE_PRODUIT_id_type_produit"));
//            produit.setTypeProduit(typeProduit);
            return commande;
        }
    };
}
