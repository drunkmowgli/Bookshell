package org.asm.labs.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "authors")
public class Author {
    
    @Id
    private String id;
    
    private String name;
    
    public Author() {
    }
    
    public Author(String name) {
        this.name = name;
    }
    
    public String getId() {
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