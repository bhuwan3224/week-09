package com.example.moviedatabase;

/**
 * Model class representing a movie.
 * Handles null/invalid data gracefully.
 */
public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    public Movie(String title, Integer year, String genre, String posterResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    // Returns title or a placeholder if null
    public String getTitle() {
        return (title != null && !title.isEmpty()) ? title : "Unknown Title";
    }

    // Returns year as string or placeholder if null/invalid
    public String getYear() {
        return (year != null && year > 0) ? String.valueOf(year) : "Unknown Year";
    }

    // Returns genre or placeholder if null
    public String getGenre() {
        return (genre != null && !genre.isEmpty()) ? genre : "Unknown Genre";
    }

    // Returns poster resource name or null
    public String getPosterResource() {
        return posterResource;
    }
}