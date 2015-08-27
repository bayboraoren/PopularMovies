package com.popularmovies.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.popularmovies.domain.Movie;
import com.popularmovies.domain.Movies;
import com.popularmovies.utility.JsonUtility;
import com.popularmovies.utility.MovieUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by bora on 25.08.2015.
 */
public class MovieService extends IntentService{

    public static final String LOG_TAG = MovieService.class.getSimpleName();


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MovieService() {
        super("movie service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        MovieUtility.getMovies();

        return;
    }


}
