package org.asm.labs.repository.impl;

import org.asm.labs.entity.Genre;
import org.asm.labs.repository.GenreRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public GenreRepositoryJpaImpl(EntityManager em) {
        this.em = em;
    }
    

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre findById(int id) throws NoResultException {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.id = :id", Genre.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(g) FROM Genre g", Long.class);
        return query.getSingleResult();
    }
}
