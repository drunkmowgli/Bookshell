package org.asm.labs.repository.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(@NotNull Author author) throws DataAccessException {
        if (author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author findById(int id) throws DataAccessException {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(@NotNull Author author) throws DataAccessException {
//        jdbc.update(
//                DELETE_AUTHOR_FROM_REFERENCE,
//                Collections.singletonMap("author_id", author.getId())
//        );
//
//        jdbc.update(
//                DELETE_AUTHOR_BY_ID,
//                Collections.singletonMap("author_id", author.getId())
//        );
        em.remove(author);
    }

//    @Override
//    public int count() {
//        return jdbc.queryForObject(
//                COUNT_AUTHORS,
//                new HashMap<>(),
//                Integer.class
//        );
//    }
}
