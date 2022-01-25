package com.example.popular_movies.retrofit;

import com.example.popular_movies.retrofit.services.MovieService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {
    private final Retrofit retrofit;

    public AppRetrofit() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new ServiceInterceptor())
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb8.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public MovieService getMovies() {
        return retrofit.create(MovieService.class);
    }

}
