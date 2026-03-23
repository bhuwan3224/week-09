package com.example.moviedatabase;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for a single movie item in the RecyclerView.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    TextView yearTextView;
    TextView genreTextView;
    TextView posterTextView; // Shows poster name as text (no actual images needed)

    public MovieViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.textTitle);
        yearTextView  = itemView.findViewById(R.id.textYear);
        genreTextView = itemView.findViewById(R.id.textGenre);
        posterTextView = itemView.findViewById(R.id.textPoster);
    }

    /**
     * Binds a Movie object to the view, showing placeholders for missing data.
     */
    public void bind(Movie movie) {
        titleTextView.setText(movie.getTitle());
        yearTextView.setText("Year: " + movie.getYear());
        genreTextView.setText("Genre: " + movie.getGenre());

        String poster = movie.getPosterResource();
        posterTextView.setText(poster != null ? "🎞 " + poster : "🎞 No poster");
    }
}