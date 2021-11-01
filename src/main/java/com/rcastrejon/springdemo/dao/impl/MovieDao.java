package com.rcastrejon.springdemo.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.rcastrejon.springdemo.dao.Dao;
import com.rcastrejon.springdemo.model.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieDao implements Dao<Movie> {
    private Logger logger = LoggerFactory.getLogger(MovieDao.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<Movie> rowMapper = (rs, rowNum) -> {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setReleaseDate(rs.getString("release_date"));
        movie.setOverview(rs.getString("overview"));
        return movie;
    };

    @Autowired
    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Movie> get(int id) {
        String sql = "SELECT * FROM movie WHERE id=?";
        Movie movie = null;
        try {
            movie = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { id });
        } catch (DataAccessException ex) {
            logger.info("movie with id " + id + " not found.");
        }
        return Optional.ofNullable(movie);
    }

    @Override
    public List<Movie> getAll() {
        String sql = "SELECT * FROM movie";
        List<Movie> movies = jdbcTemplate.query(sql, rowMapper);
        return movies;
    }

    @Override
    public int create(Movie movie) {
        String sql = "INSERT INTO movie(title,release_date,overview) VALUES(?,?,?)";
        return jdbcTemplate.update(sql, movie.getTitle(), movie.getReleaseDate(), movie.getOverview());
    }

    @Override
    public int update(int id, Movie movie) {
        String sql = "UPDATE movie SET title=?,release_date=?,overview=? WHERE id=?";
        return jdbcTemplate.update(sql, movie.getTitle(), movie.getReleaseDate(), movie.getOverview(), id);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM movie WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

}
