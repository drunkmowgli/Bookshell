package org.asm.labs.repository.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(@NotNull Author author) {
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
    public Author findById(int id) throws NoResultException {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(@NotNull Author author) {
        em.remove(author);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(a) FROM Author a", Long.class);
        return query.getSingleResult();
    }
}
