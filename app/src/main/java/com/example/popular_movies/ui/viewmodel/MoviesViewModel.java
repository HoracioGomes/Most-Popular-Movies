package com.example.popular_movies.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popular_movies.model.MovieDetails;
import com.example.popular_movies.repository.MoviesRepository;
import com.example.popular_movies.repository.listeners.ListenerGetDetailMovie;
import com.example.popular_movies.repository.listeners.ListenerGetListMovies;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    public MutableLiveData<ResourceResponseMovies> liveDataMovies = new MutableLiveData<ResourceResponseMovies>();
    public MutableLiveData<MovieDetails> liveDataMovieDetail = new MutableLiveData<MovieDetails>();

    MoviesRepository repositoryMovies = new MoviesRepository();

    public void getMovies() {
        repositoryMovies.getMovies(new ListenerGetListMovies() {

            @Override
            public void successGetList(List<MovieDetails> listMovies, int codeResponse) {
                ResourceResponseMovies resource = new ResourceResponseMovies(codeResponse, listMovies);
                liveDataMovies.setValue(resource);
            }

            @Override
            public void failGetList(String error) {
                // Implementar falha
            }
        });

    }


    public void getDetails(String title) {
        repositoryMovies.getDetailMovie(title, "US", new ListenerGetDetailMovie() {
            @Override
            public void successGetDetail(MovieDetails movie) {
                liveDataMovieDetail.setValue(movie);
            }

            @Override
            public void failGetDetails(String error) {
                // Implementar falha
            }
        });
    }

}