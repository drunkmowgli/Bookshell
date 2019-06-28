package org.asm.labs.entity;


import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "genre_name")
    private String genreName;

    public Genre() {}

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
