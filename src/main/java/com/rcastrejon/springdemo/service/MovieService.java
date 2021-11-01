package com.rcastrejon.springdemo.service;

import java.util.List;
import java.util.Optional;

import com.rcastrejon.springdemo.dao.impl.MovieDao;
import com.rcastrejon.springdemo.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieDao movieDao;

    @Autowired
    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Optional<Movie> get(int movieId) {
        Optional<Movie> movie = movieDao.get(movieId);
        return movie;
    }

    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    public void create(Movie movie) {
        movieDao.create(movie);
    }

    public int delete(int movieId) {
        return movieDao.delete(movieId);
    }
}
