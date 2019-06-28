package org.asm.labs.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.boot.jaxb.hbm.internal.CacheAccessTypeConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "title")
    private String title;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;
    
    public Book() {
    }
    
    public Book(int id, String title, List<Author> authors, Genre genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }
    
    public Book(String title, List<Author> authors, Genre genre) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<Author> getAuthors() {
        return authors;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", authors=" + authors +
            ", genre=" + genre +
            '}';
    }
}
