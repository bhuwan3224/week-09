package com.example.moviedatabase;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for loading and parsing movie data from a local JSON file.
 */
public class JsonUtils {

    private static final String TAG = "JsonUtils";
    private static final String JSON_FILE = "movies.json";

    /**
     * Loads movies from the assets/movies.json file.
     * Handles file not found, JSON parsing errors, and invalid data.
     *
     * @param context Application context
     * @return List of valid Movie objects (may be empty if all entries are invalid)
     * @throws IOException if the file cannot be found or read
     */
    public static List<Movie> loadMoviesFromJson(Context context) throws IOException {
        List<Movie> movies = new ArrayList<>();
        String jsonString = readJsonFile(context);

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    // Skip empty objects
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.length() == 0) {
                        Log.w(TAG, "Entry " + i + ": empty object, skipping.");
                        continue;
                    }

                    Movie movie = parseMovie(obj, i);
                    if (movie != null) {
                        movies.add(movie);
                    }

                } catch (Exception e) {
                    // Log and skip any individual bad entry
                    handleJsonException(e, "Entry " + i);
                }
            }

        } catch (Exception e) {
            // Top-level JSON is malformed
            handleJsonException(e, "JSON file");
            throw new IOException("Failed to parse JSON: " + e.getMessage());
        }

        return movies;
    }

    /**
     * Reads the raw JSON string from the assets folder.
     */
    private static String readJsonFile(Context context) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = context.getAssets().open(JSON_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new IOException("movies.json not found in assets.", e);
        }
        return sb.toString();
    }

    /**
     * Parses a single JSONObject into a Movie.
     * Returns null if the entry is completely unusable (e.g., both title and year missing).
     */
    private static Movie parseMovie(JSONObject obj, int index) {
        // --- Title ---
        String title = null;
        if (obj.has("title") && !obj.isNull("title")) {
            title = obj.optString("title", null);
        } else {
            Log.w(TAG, "Entry " + index + ": missing or null title.");
        }

        // --- Year ---
        Integer year = null;
        if (obj.has("year")) {
            try {
                double rawYear = obj.optDouble("year", -1);
                if (Double.isNaN(rawYear) || rawYear <= 0) {
                    Log.w(TAG, "Entry " + index + ": invalid year value.");
                } else if (rawYear != Math.floor(rawYear)) {
                    // Non-integer year like 1972.5
                    Log.w(TAG, "Entry " + index + ": non-integer year " + rawYear + ", rounding.");
                    year = (int) rawYear;
                } else {
                    year = (int) rawYear;
                }
            } catch (Exception e) {
                // year might be a string like "nineteen-ninety-four"
                Log.w(TAG, "Entry " + index + ": year is not a number - " + obj.opt("year"));
            }
        } else {
            Log.w(TAG, "Entry " + index + ": missing year field.");
        }

        // --- Genre ---
        String genre = null;
        if (obj.has("genre") && !obj.isNull("genre")) {
            genre = obj.optString("genre", null);
        } else {
            Log.w(TAG, "Entry " + index + ": missing or null genre.");
        }

        // --- Poster ---
        String poster = null;
        if (obj.has("poster") && !obj.isNull("poster")) {
            poster = obj.optString("poster", null);
        } else {
            Log.w(TAG, "Entry " + index + ": missing or null poster.");
        }

        // Skip entries with NO usable fields at all
        if (title == null && year == null && genre == null && poster == null) {
            Log.e(TAG, "Entry " + index + ": no usable fields, skipping.");
            return null;
        }

        return new Movie(title, year, genre, poster);
    }

    /**
     * Logs JSON-related exceptions for debugging.
     */
    private static void handleJsonException(Exception e, String context) {
        Log.e(TAG, "JSON error in [" + context + "]: " + e.getMessage());
    }
}