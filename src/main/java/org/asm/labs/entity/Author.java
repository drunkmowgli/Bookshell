package org.asm.labs.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Book> books;

    public Author() {}

    public Author(String authorName) {
        this.name = authorName;
    }

    public Author(int id, String authorName) {
        this.id = id;
        this.name = authorName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}