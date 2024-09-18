package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DAOAuthentificationMySQL implements IDAOAuthentification {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final RowMapper<Utilisateur> UTILISATEUR_ROW_MAPPER = new RowMapper<Utilisateur>() {
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setPassword(rs.getString("mot_de_passe"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setCodePostal(rs.getInt("code_postal"));
            return utilisateur;
        }
    };

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return jdbcTemplate.query("SELECT * FROM utilisateur", UTILISATEUR_ROW_MAPPER);
    }

    @Override
    public Utilisateur getUtilisateurById(long id) {
        List<Utilisateur> utilisateurs = jdbcTemplate.query("SELECT * FROM UTILISATEUR WHERE id_utilisateur = ?", UTILISATEUR_ROW_MAPPER, id);
        if (utilisateurs.isEmpty()) {
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
        List<Utilisateur> utilisateurs = jdbcTemplate.query("SELECT * FROM UTILISATEUR WHERE email = ?", UTILISATEUR_ROW_MAPPER, email);
        if (utilisateurs.size() == 0) {
            return null;
        }
        return utilisateurs.get(0);
    }

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        if ((utilisateur.getIdUtilisateur()) != null) {

            jdbcTemplate.update("UPDATE UTILISATEUR SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, rue = ?, code_postal = ?, ville = ? WHERE id_utilisateur = ?",
                    utilisateur.getNom(),
                    utilisateur.getPrenom(),
                    utilisateur.getEmail(),
                    utilisateur.getPassword(),
                    utilisateur.getRue(),
                    utilisateur.getCodePostal(),
                    utilisateur.getVille(),
                    utilisateur.getIdUtilisateur());

            return;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_id_utilisateur", utilisateur.getIdUtilisateur());
        mapSqlParameterSource.addValue("new_nom", utilisateur.getNom());
        mapSqlParameterSource.addValue("new_prenom", utilisateur.getPrenom());
        mapSqlParameterSource.addValue("new_email", utilisateur.getEmail());
        mapSqlParameterSource.addValue("new_mot_de_passe", utilisateur.getPassword());
        mapSqlParameterSource.addValue("new_rue", utilisateur.getRue());
        mapSqlParameterSource.addValue("new_code_postal", utilisateur.getCodePostal());
        mapSqlParameterSource.addValue("new_ville", utilisateur.getVille());


        namedParameterJdbcTemplate.update("INSERT INTO utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, rue, code_postal, ville) VALUES (:new_id_utilisateur, :new_nom, :new_prenom, :new_email, :new_mot_de_passe, :new_rue, :new_code_postal, :new_ville)", mapSqlParameterSource, keyHolder);

        if(keyHolder.getKey() != null) {
            System.out.println(keyHolder.getKey().longValue());
            utilisateur.setIdUtilisateur(keyHolder.getKey().longValue());
        }
    }
    @Override
    public void deleteUserById(Long id)
    {
        int nbSuppression = jdbcTemplate.update("DELETE utilisateur FROM utilisateur WHERE id_utilisateur = ?", id);
        System.out.println("Produit supprimé");
    }
}

