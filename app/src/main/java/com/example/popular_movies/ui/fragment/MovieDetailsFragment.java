package com.example.popular_movies.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.popular_movies.R;
import com.example.popular_movies.model.MovieDetails;
import com.example.popular_movies.ui.viewmodel.MoviesViewModel;
import com.squareup.picasso.Picasso;

import static com.example.popular_movies.enums.FragmentArguments.TITLE_ARGUMENT;

public class MovieDetailsFragment extends Fragment {

    private MoviesViewModel mViewModel;
    Picasso picasso = Picasso.get();

    String argumentTitle;
    String titleToApi;
    TextView title;
    ImageView imageView;
    TextView releasedDate;
    TextView labelReleasedDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArgument();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.titulo_filme_movie_details);
        imageView = view.findViewById(R.id.image_movie_details);
        releasedDate = view.findViewById(R.id.release_movie_details);
        labelReleasedDate = view.findViewById(R.id.label_release);
        configViewModel();
    }

    private void configViewModel() {
        mViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        mViewModel.liveDataMovieDetail.observe((LifecycleOwner) requireContext(), movieDetails -> {
            title.setText(movieDetails.getTitle().getTitle());
            releasedDate.setText(movieDetails.getReleasedDate());
            labelReleasedDate.setVisibility(View.VISIBLE);
            loadImage(movieDetails);

        });
        mViewModel.getDetails(titleToApi);
    }

    private void loadImage(MovieDetails movieDetails) {
        try {
            picasso.load(movieDetails.getTitle().getImage().getUrl()).into(imageView);
        } catch (Exception e) {
        }
    }

    private void getArgument() {
        Bundle data = getArguments();
        if (data.getSerializable(TITLE_ARGUMENT.toString()) != null) {
            argumentTitle = (String) data.getSerializable(TITLE_ARGUMENT.toString());
            titleToApi = argumentTitle.substring(7).replace("/", "");
        }
    }
}