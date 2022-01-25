package com.example.popular_movies.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popular_movies.R;
import com.example.popular_movies.model.MovieDetails;
import com.example.popular_movies.ui.fragment.MovieDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.popular_movies.enums.FragmentArguments.TITLE_ARGUMENT;
import static com.example.popular_movies.enums.Tags.TAG_FRAGMENT_DETAILS_MOVIE;

public class ListPopularMoviesAdapter extends RecyclerView.Adapter<com.example.popular_movies.ui.adapter.ListPopularMoviesAdapter.ListPopularViewHolder> {

    private List<MovieDetails> moviesList = new ArrayList<>();
    private Context context;
    private FragmentManager fragmentManager;

    public ListPopularMoviesAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void updateList(List<MovieDetails> newList) {
        moviesList.clear();
        moviesList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);
        return new ListPopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPopularViewHolder holder, int position) {
        holder.add(moviesList.get(position));

        holder.itemView.setOnClickListener(view -> moveToDetailsMovieFragment(position));
    }

    private void moveToDetailsMovieFragment(int position) {
        try {
            String stringTitle = moviesList.get(position).getId();
            MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
            Bundle data = new Bundle();
            data.putSerializable(TITLE_ARGUMENT.toString(), stringTitle);
            movieDetailsFragment.setArguments(data);

            int container = R.id.container;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(container, movieDetailsFragment, TAG_FRAGMENT_DETAILS_MOVIE.toString());
            transaction.commit();
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class ListPopularViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView coverImageView;

        public ListPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.name_card_movie);
            coverImageView = itemView.findViewById(R.id.movie_cover_list);
        }

        public void add(MovieDetails movieDetails) {
            try {
                String title = movieDetails.getTitle().getTitle();
                textViewTitle.setText(title);
                Picasso picasso = Picasso.get();
                picasso.load(movieDetails.getTitle().getImage().getUrl())
                        .into(coverImageView);
            } catch (Exception e) {
            }
        }
    }
}
