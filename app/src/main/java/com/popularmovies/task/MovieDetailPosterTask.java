package com.popularmovies.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.utility.MovieUtility;

/**
 * Created by baybora on 8/30/15.
 */
public class MovieDetailPosterTask extends AsyncTask<String,Void,Bitmap> {

    private ImageView moviePoster;

    public MovieDetailPosterTask(ImageView moviePoster){
        this.moviePoster = moviePoster;
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        return MovieUtility.getMovieDetailPoster(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap moviePoster) {
        this.moviePoster.setImageBitmap(moviePoster);
    }
}
