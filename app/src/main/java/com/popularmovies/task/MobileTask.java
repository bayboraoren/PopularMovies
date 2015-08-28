package com.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;

import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.domain.Movies;
import com.popularmovies.utility.MovieUtility;

/**
 * Created by baybora on 8/27/15.
 */
public class MobileTask extends AsyncTask<Void,Void,Movies>{

    private MovieViewAdapter movieViewAdapter;
    private Context context;

    public MobileTask(MovieViewAdapter movieViewAdapter,Context context){
        this.movieViewAdapter = movieViewAdapter;
        this.context = context;
    }


    @Override
    protected Movies doInBackground(Void... voids) {
        return MovieUtility.getMovies(context);
    }

    @Override
    protected void onPostExecute(Movies movies) {
        movieViewAdapter.addMovies(movies);
    }
}
