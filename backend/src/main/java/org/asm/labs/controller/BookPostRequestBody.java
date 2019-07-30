package org.asm.labs.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "authors",
        "genre"
})
public class BookPostRequestBody {

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private String authors;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("title")
    String getTitle() {
        return title;
    }

    @JsonProperty("authors")
    String getAuthors() {
        return authors;
    }

    @JsonProperty("genre")
    String getGenre() {
        return genre;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("authors")
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @JsonProperty("genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
