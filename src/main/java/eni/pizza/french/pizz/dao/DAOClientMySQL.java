package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOClientMySQL implements IDAOClient{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void saveClient(Client client) {
        if ((client.getIdUtilisateur()) != null) {

            jdbcTemplate.update("UPDATE CLIENT SET nom = ?, prenom = ?, rue = ?, code_postal = ?, ville = ? WHERE id_client = ?",
                    client.getNom(),
                    client.getPrenom(),
                    client.getEmail(),
                    client.getPassword());

            return;
        }
        jdbcTemplate.update("INSERT INTO CLIENT (nom, prenom, email, mot_de_passe) VALUES (?, ?, ?, ?)", client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getPassword());
    }
}
