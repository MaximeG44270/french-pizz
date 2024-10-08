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
public class DAOCommandesMySQL implements IDAOCommandes {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static final RowMapper<Commande> COMMANDE_ROW_MAPPER = new RowMapper<Commande>() {
        public Commande mapRow(ResultSet rs, int rowNum) throws SQLException {
            Commande commande = new Commande();
            commande.setIdCommande(rs.getLong("id_commande"));
            commande.setDateHeureLivraison(rs.getTimestamp("date_heure_livraison").toLocalDateTime());
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdUtilisateur(rs.getLong("UTILISATEUR_id_utilisateur"));
            commande.setUtilisateur(utilisateur);
            commande.setLivraison(rs.getInt("livraison"));
            EtatCommande etatCommande = new EtatCommande();
            etatCommande.setIdEtat(rs.getLong("ETAT_id_etat"));
            commande.setEtatCommande(etatCommande);
            commande.setPrixTotal(rs.getDouble("prix_total"));
            commande.setEstPayé(rs.getBoolean("est_paye"));
            return commande;
        }
    };
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Commande> findAllCommandesPizzaolo() {

        return jdbcTemplate.query("SELECT * FROM commande WHERE ETAT_id_etat = 1", COMMANDE_ROW_MAPPER);
    }
    @Override
    public List<Commande> findAllCommandesGerant() {
        return jdbcTemplate.query("SELECT * FROM commande", COMMANDE_ROW_MAPPER);
    }
    @Override
    public List<Commande> findAllCommandesLivreur() {
        return jdbcTemplate.query("SELECT * FROM commande WHERE livraison==1 && (ETAT_id_etat = 1 || ETAT_id_etat = 2)", COMMANDE_ROW_MAPPER);
    }
    @Override
    public Commande selectCommandeById(Long id) {

        List<Commande> commandes = jdbcTemplate.query("SELECT * FROM commande WHERE id_commande = ?", COMMANDE_ROW_MAPPER, id);

        if (commandes.size() == 0) {
            return null;
        }

        return commandes.get(0);
    }
    @Override
    public void saveCommande(Commande commande) {
        String sql;

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_id_commande", commande.getIdCommande());
        mapSqlParameterSource.addValue("new_date_heure_livraison", commande.getDateHeureLivraison());
        mapSqlParameterSource.addValue("new_id_utilisateur", commande.getUtilisateur().getIdUtilisateur());
        mapSqlParameterSource.addValue("new_livraison", commande.getLivraison());
        mapSqlParameterSource.addValue("new_id_etat_commande", commande.getEtatCommande().getIdEtat());
        mapSqlParameterSource.addValue("new_prix_total", commande.getPrixTotal());
        mapSqlParameterSource.addValue("new_est_paye", commande.isEstPayé());

        if(selectCommandeById(commande.getIdCommande()) != null){
            sql= "UPDATE commande SET date_heure_livraison = :new_date_heure_livraison, UTILISATEUR_id_utilisateur = :new_id_utilisateur, livraison = :new_livraison, ETAT_id_etat = :new_id_etat_commande, prix_total = :new_prix_total, est_paye = :new_est_paye  WHERE id_commande = :new_id_commande";
        } else {
            sql = "INSERT INTO commande (id_commande, date_heure_livraison, UTILISATEUR_id_utilisateur, livraison, ETAT_id_etat, prix_total, est_paye) VALUES (:new_id_commande, :new_date_heure_livraison, :new_id_utilisateur, :new_livraison, :new_id_etat_commande, :new_prix_total, :new_est_paye)";
        }

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
        System.out.println("Commande insérée en table commande de BDD db_pizza : " + commande);
    }
    @Override
    public void deleteCommandeById(Long id)
    {
        int nbSuppression = jdbcTemplate.update("DELETE commande FROM commande WHERE id_commande = ?", id);
        System.out.println("Commande supprimée");
    }


}
