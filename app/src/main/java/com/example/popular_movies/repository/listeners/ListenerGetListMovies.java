package com.example.popular_movies.repository.listeners;

import com.example.popular_movies.model.MovieDetails;

import java.util.List;

public interface ListenerGetListMovies {
    void successGetList(List<MovieDetails> listMovies, int codeResponse);

    void failGetList(String error);
}
