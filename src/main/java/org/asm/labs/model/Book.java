package org.asm.labs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@NamedEntityGraph(name = "bookGraph", includeAllAttributes = true)
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "title")
    private String title;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    
    public Book() {
    }
    
    public Book(long id, String title, Set<Author> authors, Genre genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }
    
    public Book(String title, Set<Author> authors, Genre genre) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }
    
    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public Set<Author> getAuthors() {
        return authors;
    }
    
    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", genre=" + genre +
            ", authors=" + authors +
            '}';
    }
}
