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

import retrofit.RestAdapter;

/**
 * Created by baybora on 8/27/15.
 */
public class MovieUtility {

    public static final String LOG_TAG = MovieUtility.class.getSimpleName();

    public static final Bitmap getMovieDetailPoster(String posterPath) {

        String urlDisplay = "http://image.tmdb.org/t/p/w500" +
                "/" + posterPath;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            return null;
        }
    }

    public static final Movies getMovies(Context context) {

        final String MOVIE_DATABASE_BASE_URL =
                "http://api.themoviedb.org/3";

        String sortBy = CommonUtility.getPreferredSortBy(context);

        String apiKey = "c8d8be2cdf2ddd5cb31cf3a04460210c";


        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(MOVIE_DATABASE_BASE_URL).build();
        MovieRestService service = retrofit.create(MovieRestService.class);

        return service.getMovies(apiKey, sortBy);

    }

}
