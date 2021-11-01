package com.rcastrejon.springdemo.controller;

import java.util.List;
import java.util.Optional;

import com.rcastrejon.springdemo.model.Movie;
import com.rcastrejon.springdemo.model.request.MovieRequest;
import com.rcastrejon.springdemo.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<Object> getMovies() {
        List<Movie> movies = movieService.getAll();
        if (movies.isEmpty()) {
            return ResponseEntity.ok().body("No movies found.");
        }
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getMoviesById(@PathVariable("id") String movieId) {
        Movie movie = null;
        try {
            int movieIdInt = Integer.valueOf(movieId);
            Optional<Movie> movieOpt = movieService.get(movieIdInt);
            if (movieOpt.isEmpty()) {
                return ResponseEntity.ok().body("Movie with id " + movieId + " was not found.");
            }
            movie = movieOpt.get();
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().body("Id is not a number.");
        }
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping()
    public ResponseEntity<Object> postMovie(@RequestBody MovieRequest entity) {
        if (!entity.validate()) {
            return ResponseEntity.badRequest().body("Missing body parameters.");
        }

        movieService.create(entity.toMovie());
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") String movieId) {
        int delete;
        try {
            int id = Integer.valueOf(movieId);
            delete = movieService.delete(id);
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().body("Id is not a number.");
        }

        if (delete == 1) {
            return ResponseEntity.ok().body("Movie with id " + movieId + " succesfully deleted.");
        } else {
            return ResponseEntity.ok().body("Movie not found.");
        }
    }

}
