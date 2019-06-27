package org.asm.labs.entity;


import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author_name")
    private String name;

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
