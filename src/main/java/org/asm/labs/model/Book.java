package org.asm.labs.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    @DBRef
    private Genre genre;

    @DBRef
    private Set<Author> authors;

    public Book() {
    }

    public Book(String id, String title, Set<Author> authors, Genre genre) {
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

    public String getId() {
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
