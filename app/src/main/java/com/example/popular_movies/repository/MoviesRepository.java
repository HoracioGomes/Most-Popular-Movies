package com.example.popular_movies.repository;

import com.example.popular_movies.model.MovieDetails;
import com.example.popular_movies.repository.listeners.ListenerGetDetailMovie;
import com.example.popular_movies.repository.listeners.ListenerGetListMovies;
import com.example.popular_movies.retrofit.AppRetrofit;
import com.example.popular_movies.retrofit.services.MovieService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    MovieService movieService = new AppRetrofit().getMovies();

    public void getMovies(ListenerGetListMovies listener) {

        Call<List<String>> callMovies = movieService.getMovies();

        callMovies.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                try {
                    List<MovieDetails> listDetailsMovies = new ArrayList<>();
                    List<String> listStringIdMovies = new ArrayList<>();

                    if (response.code() != 200) {
                        listener.successGetList(listDetailsMovies, response.code());
                    }

                    listStringIdMovies.addAll(response.body());

                    for (int i = 0; i < 10; i++) {
                        String idString = listStringIdMovies.get(i);
                        String idExtracted = idString.substring(7).replace("/", "");

                        Call<MovieDetails> callMovieDetail = movieService.getDetailMovie(idExtracted, "US");
                        callMovieDetail.enqueue(new Callback<MovieDetails>() {
                            @Override
                            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                                listDetailsMovies.add(response.body());

                                if (listDetailsMovies.size() == 10) {
                                    listener.successGetList(listDetailsMovies, response.code());
                                }

                            }

                            @Override
                            public void onFailure(Call<MovieDetails> call, Throwable t) {
                                //Tratar erro
                            }
                        });

                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

                listener.failGetList(t.toString());

            }
        });

    }


    public void getDetailMovie(String title, String country, ListenerGetDetailMovie listener) {

        Call<MovieDetails> callMovieDetail = movieService.getDetailMovie(title, country);

        callMovieDetail.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                listener.successGetDetail(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                listener.failGetDetails(t.toString());

            }
        });

    }


}

