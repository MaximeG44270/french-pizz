package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Commande;
import eni.pizza.french.pizz.bo.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DAOAuthentificationMySQL implements IDAOAuthentification{
    JdbcTemplate jdbcTemplate;
    static final RowMapper<Utilisateur> UTILISATEUR_ROW_MAPPER = new RowMapper<Utilisateur>() {
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            Commande commande = new Commande();
            commande.setIdCommande(rs.getLong("COMMANDE_id_commande"));
            utilisateur.setCommande(commande);
            return utilisateur;
        }
    };
    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return jdbcTemplate.query("SELECT * FROM utilisateur", UTILISATEUR_ROW_MAPPER);
    }
    @Override
    public Utilisateur getUtilisateurById(long id)
    {
        List<Utilisateur> utilisateurs = jdbcTemplate.query("SELECT * FROM UTILISATEUR WHERE id_utilisateur = ?", UTILISATEUR_ROW_MAPPER, id);
        if (utilisateurs.isEmpty())
        {
            return null;
        }
        return utilisateurs.get(0);
    }
    @Override
    public Utilisateur getUtilisateurByEmailAndPassword(String email, String password) {
        List<Utilisateur> utilisateurs = jdbcTemplate.query("SELECT * FROM UTILISATEUR WHERE email = ? AND mot_de_passe = ?", UTILISATEUR_ROW_MAPPER, email, password);
        if (utilisateurs.size() == 0) {
            return null;
        }
        return utilisateurs.get(0);

    }
    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        List <Utilisateur> utilisateurs = jdbcTemplate.query("SELECT * FROM UTILISATEUR WHERE email = ?", UTILISATEUR_ROW_MAPPER, email);
        if (utilisateurs.size() == 0) {
            return null;
        }
        return utilisateurs.get(0);
    }
    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        if (getUtilisateurByEmail(utilisateur.getEmail()) != null) {
            jdbcTemplate.update("UPDATE UTILISATEUR SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, COMMANDE_id_commande = ? WHERE id_utilisateur = ?",
                    utilisateur.getNom(),
                    utilisateur.getPrenom(),
                    utilisateur.getEmail(),
                    utilisateur.getPassword(),
                    utilisateur.getCommande().getIdCommande());
            return;
        }
        jdbcTemplate.update("INSERT INTO UTILISATEUR (nom, prenom, email, mot_de_passe, COMMANDE_id_commande) VALUES (?, ?, ?, ?, ?)",
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                utilisateur.getCommande().getIdCommande());
        }
    }

