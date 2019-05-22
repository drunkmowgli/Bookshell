package org.asm.labs.dao.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.entity.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorName", author.getName());
        try {
            jdbc.update(
                    "insert into authors (author_name) values (:authorName) on conflict do nothing",
                    params);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "select * from authors",
                new HashMap<>(),
                new AuthorMapper()
        );
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorName", name);
        return jdbc.queryForObject(
                "select * from authors where author_name = :authorName",
                params,
                new AuthorMapper()
        );
    }

    @Override
    public Author getById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        return jdbc.queryForObject(
                "select * from authors where id = :ID",
                params,
                new AuthorMapper()
        );
    }

    @Override
    public void remove(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("authorName", author.getName());
        try {
            jdbc.update(
                    "delete from authors where (author_name) = :authorName",
                    params
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from authors",
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
