package com.example.popular_movies.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popular_movies.R;
import com.example.popular_movies.model.MovieDetails;
import com.example.popular_movies.ui.adapter.ListPopularMoviesAdapter;
import com.example.popular_movies.ui.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListMoviesFragment extends Fragment {

    ListPopularMoviesAdapter adapter;
    RecyclerView recyclerView;
    List<MovieDetails> listMovies = new ArrayList<>();

    private MoviesViewModel mViewModel;

    public static ListMoviesFragment newInstance() {
        return new ListMoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_movies_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configAdapter(view);
        configObserver();
    }

    private void configAdapter(@NonNull View view) {
        recyclerView = view.findViewById(R.id.movie_list_recyclerview);
        adapter = new ListPopularMoviesAdapter(requireContext(), getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.updateList(listMovies);
    }

    private void configObserver() {
        mViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        mViewModel.liveDataMovies.observe((LifecycleOwner) requireContext(), movies -> {

            if (movies.getCodeResponse() == 200) {
                listMovies.clear();
                listMovies.addAll(movies.getMovies());
                adapter.updateList(listMovies);
            } else if (movies.getCodeResponse() == 429) {
                Toast.makeText(requireContext(), getString(R.string.msg_requisicoes_ultrapassadas), Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.getMovies();
    }


}