package com.example.moviedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity that displays the movie list using a RecyclerView.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies = new ArrayList<>();
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTextView = findViewById(R.id.textError);
        movieRecyclerView = findViewById(R.id.recyclerViewMovies);

        setupRecyclerView();
        loadMovieData();
    }

    /**
     * Configures the RecyclerView with a LinearLayoutManager and the MovieAdapter.
     */
    private void setupRecyclerView() {
        adapter = new MovieAdapter(movies);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setAdapter(adapter);
    }

    /**
     * Loads movie data from JSON and updates the adapter.
     * Shows an error message if loading fails.
     */
    private void loadMovieData() {
        try {
            List<Movie> loadedMovies = JsonUtils.loadMoviesFromJson(this);

            if (loadedMovies.isEmpty()) {
                showError("No valid movies found in the data file.");
            } else {
                errorTextView.setVisibility(View.GONE);
                adapter.updateMovies(loadedMovies);
                Toast.makeText(this, loadedMovies.size() + " movies loaded.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            showError("Failed to load movies: " + e.getMessage());
        }
    }

    /**
     * Displays a user-friendly error message on screen.
     */
    private void showError(String message) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(message);
    }
}