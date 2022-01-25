package com.example.popular_movies.retrofit.services;

import com.example.popular_movies.model.MovieDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("title/get-most-popular-movies?homeCountry=US&purchaseCountry=US&currentCountry=US")
    Call<List<String>> getMovies();

    @GET("title/get-overview-details")
    Call<MovieDetails> getDetailMovie(@Query("tconst") String title, @Query("currentCountry") String currentCountry );
}
