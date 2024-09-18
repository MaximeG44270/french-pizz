package eni.pizza.french.pizz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAORoleUtilisateurMySQL implements IDAORoleUtilisateur {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public void saveRolebyId(Long id) {
        String sql;

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_id_utilisateur", id);

        namedParameterJdbcTemplate.update("INSERT INTO role_utilisateur(UTILISATEUR_id_utilisateur, ROLE_id_role) VALUES (:new_id_utilisateur, 4)", mapSqlParameterSource);
        System.out.println("Role ajouté : Client");

    }
}
