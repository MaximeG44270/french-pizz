package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.EtatCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DAOEtatCommandeMySQL implements IDAOEtatCommande{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final RowMapper<EtatCommande> ETAT_COMMANDE_ROW_MAPPER = new RowMapper<EtatCommande>() {
        public EtatCommande mapRow(ResultSet rs, int rowNum) throws SQLException {
            EtatCommande etatCommande = new EtatCommande();
            etatCommande.setIdEtat(rs.getLong("id_etat"));
            etatCommande.setLibelle(rs.getString("libelle"));
            return etatCommande;
        }
    };
    @Override
    public EtatCommande selectEtatCommandeById(Long id) {

        EtatCommande etatCommande = jdbcTemplate.queryForObject("SELECT * FROM etat WHERE id_etat = ?", ETAT_COMMANDE_ROW_MAPPER, id);

        if (etatCommande == null) {
            return null;
        }

        return etatCommande;
    }
}
