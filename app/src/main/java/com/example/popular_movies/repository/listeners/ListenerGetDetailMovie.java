package com.example.popular_movies.repository.listeners;

import com.example.popular_movies.model.MovieDetails;

public interface ListenerGetDetailMovie {

    void successGetDetail(MovieDetails movie);
    void failGetDetails(String error);
}
