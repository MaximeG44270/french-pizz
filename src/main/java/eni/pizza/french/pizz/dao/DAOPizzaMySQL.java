package eni.pizza.french.pizz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOPizzaMySQL {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DAOMovieMySQLJdbc(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Le code qui permet de savoir comment relier/convertir (mapper) un résultat SQL en objet Java
     * autrement dit comment récupérer un movie de la table movie de notre BDD MySql db_movie
     * et l'injecter sous forme d'instance de class Movie sous java
     */
    static final RowMapper<Movie> MOVIE_ROW_MAPPER = new RowMapper<Movie>() {
        //Chaque ligne de la table movie de la BDD db_movie va correspondre à un ResultSet rs

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            //les étiquettes en vert entre "" correspondent aux noms des colonnes sous la BDD SQL
            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setYear(rs.getInt("year"));
            movie.setDuration(rs.getInt("duration"));
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setNote(rs.getInt("note"));
            movie.setUrlImage(rs.getString("urlImage"));

            Genre genre = new Genre();
            genre.setId(rs.getLong("id_genre"));
            movie.setGenre(genre);

            Participant movieDirector = new Participant();
            movieDirector.setId(rs.getLong("id_movie_director"));
            movie.setMovieDirector(movieDirector);

            return movie;
        }
    };


    @Override
    public List<Movie> findAllMovies() {
        // on requête la base SQL via JdbcTemplate avec une extraction de tous les movies
        return jdbcTemplate.query("SELECT * FROM movie", MOVIE_ROW_MAPPER);
    }

    @Override
    public Movie selectMovieById(int myId) {
        // on requête la base SQL via JdbcTemplate avec une sélection de tous les movies avec comme condition id = myId
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movie WHERE id = ?", MOVIE_ROW_MAPPER, myId);

        //si on ne trouve aucun movie avec cet id alors on retourne null
        if (movies.size() == 0) {
            return null;
        }

        return movies.get(0);
    }

    @Override
    public Movie selectMovieByTitle(String thisTitle) {
        // on requête la base SQL via JdbcTemplate avec une sélection de tous les movies avec comme condition title = thisTitle
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movie WHERE title = ?", MOVIE_ROW_MAPPER, thisTitle);

        //si on ne trouve aucun movie avec ce title alors on retourne null
        if (movies.size() == 0) {
            return null;
        }

        return movies.get(0);
    }

    @Override
    public void saveMovie(Movie movie) {
        //V1
        //Tester si le movie existe en base, si OUI => UPADTE, si NON => INSERT
        //Solution finale : on vérifiera que le movie n'est pas déjà présent par son id
        //if(movie.getId() != 0 && selectMovieById(movie.getId()) != null){}
        //Solution temporaire : on vérifiera que le movie n'est pas déjà présent par son title
        /*if(selectMovieByTitle(movie.getTitle()) != null){
            jdbcTemplate.update("UPDATE movie SET note = ?, year = ?, duration = ?, synopsis = ? WHERE title = ?",
                    movie.getNote(), movie.getYear(), movie.getDuration(), movie.getSynopsis(), movie.getTitle());
            return;
        }

        jdbcTemplate.update("INSERT INTO movie(title, note, year, duration, synopsis) VALUES (?, ?, ?, ?, ?)",
                movie.getTitle(), movie.getNote(), movie.getYear(), movie.getDuration(), movie.getSynopsis());*/

        //V2
        String sql;

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("myId", movie.getId());
        mapSqlParameterSource.addValue("myTitle", movie.getTitle());
        mapSqlParameterSource.addValue("myNote", movie.getNote());
        mapSqlParameterSource.addValue("myYear", movie.getYear());
        mapSqlParameterSource.addValue("myDuration", movie.getDuration());
        mapSqlParameterSource.addValue("mySynopsis", movie.getSynopsis());
        //mapSqlParameterSource.addValue("myUrlImage", movie.getUrlImage());
        mapSqlParameterSource.addValue("myGenreId", movie.getGenre().getId());

        if(selectMovieByTitle(movie.getTitle()) != null){
            sql= "UPDATE movie SET note = :myNote, year = :myYear, duration = :myDuration, synopsis = :mySynopsis, id_genre = :myGenreId WHERE title = :myTitle";
        } else {
            sql = "INSERT INTO movie (title, note, year, duration, synopsis, id_genre) VALUES (:myTitle, :myNote, :myYear, :myDuration, :mySynopsis, :myGenreId)";
        }

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
        System.out.println("Film inséré en table movie de BDD db_movie : " + movie);

    }

}
