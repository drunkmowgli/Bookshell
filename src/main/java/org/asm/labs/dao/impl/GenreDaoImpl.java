package org.asm.labs.dao.impl;

import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {this.jdbc = jdbc;}

    @Override
    public void add(Genre genre) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("genreName", genre.getGenreName());
        jdbc.update(
                "insert into genres (genre) values (:genreName)",
                params
        );

    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                "select * from genres",
                new HashMap<>(),
                new GenreMapper()
        );
    }

    @Override
    public Genre getByGenreName(String genreName) {
        Map<String, Object> params = new HashMap<>();
        params.put("genreName", genreName);
        return jdbc.queryForObject(
                "select * from genres where genre = :genreName",
                params,
                new GenreMapper()
        );
    }

    @Override
    public Genre getById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("genreId", id);
        return jdbc.queryForObject(
                "select * from genres where id = :genreId",
                params,
                new GenreMapper()
        );
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from genres",
                new HashMap<>(),
                Integer.class
        );
    }

    @Override
    public void remove(Genre genre) {
        Map<String, Object> booksParam = new HashMap<>();
        booksParam.put("genre_id", genre.getId());
        jdbc.update(
                "update books set genre_id = NULL where genre_id = :genre_id",
                booksParam
        );

        Map<String, Object> params = new HashMap<>();
        params.put("genreName", genre.getGenreName());
        jdbc.update(
                "delete from genres where genre = :genreName",
                params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String genreName = resultSet.getString("genre");
            return new Genre(id, genreName);
        }
    }
}
