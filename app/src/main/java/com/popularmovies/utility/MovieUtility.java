package com.popularmovies.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.popularmovies.domain.Movies;
import com.popularmovies.service.MovieRestService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baybora on 8/27/15.
 */
public class MovieUtility {

    public static final String LOG_TAG = MovieUtility.class.getSimpleName();

    public static final Bitmap getMovieDetailPoster(String posterPath) {

        String urlDisplay = "http://image.tmdb.org/t/p/w500" +
                "/" + posterPath;
        Bitmap moviePoster = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            moviePoster = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return moviePoster;
    }

    public static final Movies getMoviesWithRetrofit(Context context) {

        final String MOVIE_DATABASE_BASE_URL =
                "http://api.themoviedb.org/3";

        String sortBy = CommonUtility.getPreferredSortBy(context);

        String apiKey = "c8d8be2cdf2ddd5cb31cf3a04460210c";


        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(MOVIE_DATABASE_BASE_URL).build();
        MovieRestService service = retrofit.create(MovieRestService.class);

        Movies movies = service.popular(sortBy, new Callback<Movies>() {

            @Override
            public void success(Movies movies, Response response) {
                Log.i("Test", "Test");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Test", "Test");
            }
        });

        return null;
    }

    public static final Movies getMovies(Context context) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        String sortBy = CommonUtility.getPreferredSortBy(context);

        String sortOrder = sortBy;
        String apiKey = "c8d8be2cdf2ddd5cb31cf3a04460210c";

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            final String FORECAST_BASE_URL =
                    "http://api.themoviedb.org/3/discover/movie?";

            final String SORT_BY = "sort_by";
            final String API_KEY = "api_key";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_BY, sortOrder)
                    .appendQueryParameter(API_KEY, apiKey)
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            Movies movies = JsonUtility.jsonToMovies(forecastJsonStr);

            return movies;

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return null;

    }

}
