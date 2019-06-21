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
import java.util.Collections;
import java.util.List;

import static org.asm.labs.dao.impl.SqlQueryTemplates.*;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {this.jdbc = jdbc;}

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                SELECT_ALL_GENRES,
                Collections.EMPTY_MAP,
                new GenreMapper()
        );
    }

    @Override
    public Genre getById(int id) throws DataAccessException {
        return jdbc.queryForObject(
                SELECT_GENRE_BY_ID,
                Collections.singletonMap("genre_id", id),
                new GenreMapper()
        );
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                COUNT_GENRES,
                Collections.EMPTY_MAP,
                Integer.class
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
