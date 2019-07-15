package org.asm.labs.controller;

import org.asm.labs.service.GenreNotExistException;
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
        genreService.save(genreName);
    }

    @ShellMethod("get all genres")
    public void get_all_genres() {
        genreService.findAll().forEach(System.out::println);
    }

    @ShellMethod("get genre by genre's id")
    public void get_genre(@ShellOption String genreId) {
        try {
            System.out.println(genreService.findById(genreId));
        } catch (GenreNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("remove genre")
    public void remove_genre(@ShellOption String genreId) {
        try {
            genreService.remove(genreId);
        } catch (GenreNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod("count")
    public void count_genres() {
        System.out.println(genreService.count());
    }

}
