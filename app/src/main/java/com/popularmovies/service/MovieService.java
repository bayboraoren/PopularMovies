package com.popularmovies.service;

import android.app.IntentService;
import android.content.Intent;

import com.popularmovies.utility.MovieUtility;

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

        MovieUtility.getMoviesWithRetrofit(this.getApplicationContext());

        return;
    }


}
