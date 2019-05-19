package org.asm.labs.entities;

public class Author {

    private final int id;

    private final String name;

    public Author(String authorName) {
        this.id = -1;
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
