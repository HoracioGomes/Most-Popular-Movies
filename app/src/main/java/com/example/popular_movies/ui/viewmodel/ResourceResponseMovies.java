package com.example.popular_movies.ui.viewmodel;

import com.example.popular_movies.model.MovieDetails;

import java.util.List;

public class ResourceResponseMovies {
    int codeResponse;
    List<MovieDetails> movies;

    public ResourceResponseMovies(int codeResponse, List<MovieDetails> movies) {
        this.codeResponse = codeResponse;
        this.movies = movies;
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public void setCodeResponse(int codeResponse) {
        this.codeResponse = codeResponse;
    }

    public List<MovieDetails> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDetails> movies) {
        this.movies = movies;
    }
}
