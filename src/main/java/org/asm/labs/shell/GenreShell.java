package org.asm.labs.shell;

import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreAlreadyExistException;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    @Autowired
    public GenreShell(GenreService genreService) {this.genreService = genreService;}

    @ShellMethod("add genre")
    public void add_genre(@ShellOption String genreName) {
        Genre genre = new Genre(genreName);
        try {
            genreService.add(genre);
        } catch (GenreAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("get all genres")
    public void get_all_genres() {
        genreService.getAll().forEach(System.out::println);
    }

    @ShellMethod("get genre by genre's name")
    public void get_genre_by_name(@ShellOption String genreName) {
        System.out.println(genreService.getByGenreName(genreName));
    }

    @ShellMethod("get genre by genre's id")
    public void get_genre_by_id(@ShellOption int id) {
        System.out.println(genreService.getById(id));
    }

    @ShellMethod("count")
    public void count_genres() {
        System.out.println(genreService.count());
    }

    @ShellMethod("remove genre")
    public void remove_genre(String genreName) {
        Genre genre = genreService.getByGenreName(genreName);
        genreService.remove(genre);
    }
}
