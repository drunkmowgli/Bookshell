package org.asm.labs.repository.impl;

import org.asm.labs.entity.Book;
import org.asm.labs.repository.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {


    @PersistenceContext
    private EntityManager em;


    @Autowired
    public BookRepositoryJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(@NotNull Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            System.out.println(book);
            em.merge(book);
        }
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(int id) throws NoResultException {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void remove(@NotNull Book book) {
        em.remove(book);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }
}