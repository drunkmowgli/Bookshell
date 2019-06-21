package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.entity.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.asm.labs.dao.impl.SqlQueryTemplates.*;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(Author author) throws DataAccessException {
        jdbc.update(
                INSERT_AUTHOR,
                Collections.singletonMap("authorName", author.getName()));
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                SELECT_ALL_AUTHORS,
                Collections.EMPTY_MAP,
                new AuthorMapper()
        );
    }

    @Override
    public Author getById(int id) throws DataAccessException {
        return jdbc.queryForObject(
                SELECT_AUTHOR_BY_ID,
                Collections.singletonMap("author_id", id),
                new AuthorMapper()
        );
    }

    @Override
    public void remove(Author author) throws DataAccessException {
//        Map<String, Object> referenceParams = new HashMap<>();
//        referenceParams.put("author_id", author.getId());
        jdbc.update(
                DELETE_AUTHOR_FROM_REFERENCE,
                Collections.singletonMap("author_id", author.getId())
        );

//        Map<String, Object> params = new HashMap<>();
//        params.put("author_id", author.getId());
        jdbc.update(
                DELETE_AUTHOR_BY_ID,
                Collections.singletonMap("author_id", author.getId())
        );
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                COUNT_AUTHORS,
                new HashMap<>(),
                Integer.class
        );
    }

    private class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String authorName = resultSet.getString("author_name");
            return new Author(id, authorName);
        }
    }
}
