package com.rcastrejon.springdemo.model;

public class Movie {
    private int id;
    private String title;
    private String releaseDate;
    private String overview;

    public Movie() {
    }

    public Movie(String title, String releaseDate, String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s", id, title, releaseDate, overview);
    }
}
