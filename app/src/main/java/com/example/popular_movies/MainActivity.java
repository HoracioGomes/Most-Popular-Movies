package com.example.popular_movies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.popular_movies.ui.fragment.ListMoviesFragment;

import static com.example.popular_movies.enums.Tags.TAG_FRAGMENT_LIST_MOVIES;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ListMoviesFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        generateInitialFragment();
    }

    private void generateInitialFragment() {
        ListMoviesFragment listMoviesFragment = new ListMoviesFragment();
        int container = R.id.container;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, listMoviesFragment, TAG_FRAGMENT_LIST_MOVIES.toString());
        transaction.commit();

    }
}