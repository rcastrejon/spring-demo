package com.rcastrejon.springdemo.model.request;

import com.rcastrejon.springdemo.model.Movie;

public class MovieRequest {
    private String title;
    private String release_date;
    private String overview;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Boolean validate() {
        try {
            if (title.isBlank()) {
                return false;
            }
            if (release_date.isBlank()) {
                return false;
            }
            if (overview.isBlank()) {
                return false;
            }
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public Movie toMovie() {
        return new Movie(title, release_date, overview);
    }
}
