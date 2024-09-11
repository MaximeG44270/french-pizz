package eni.pizza.french.pizz.dao;

import eni.pizza.french.pizz.bo.TypeProduit;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DAOTypeProduitMySQL implements IDAOTypeProduit {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOTypeProduitMySQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<TypeProduit> findAllTypes() {
        return jdbcTemplate.query("SELECT * from type_produit", new BeanPropertyRowMapper<TypeProduit>(TypeProduit.class));
    }

    @Override
    public TypeProduit findTypeById(Long id) {
        String sql = "SELECT id_type_produit AS idTypeProduit, libelle FROM type_produit WHERE id_type_produit = :new_id";

        // Cette map va faire le lien entre toutes les clés et leurs valeurs correspondant aux noms des colonnes de la table genre dans la BDD db_pizza
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("new_id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, map, new BeanPropertyRowMapper<TypeProduit>(TypeProduit.class));
    }
}
