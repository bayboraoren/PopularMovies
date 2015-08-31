package com.popularmovies.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.popularmovies.utility.MovieUtility;

/**
 * Created by baybora on 8/30/15.
 */
public class MovieDetailPosterTask extends AsyncTask<String,Integer,Bitmap> {

    private ImageView moviePoster;
    private ProgressBar progressBar;

    public MovieDetailPosterTask(ImageView moviePoster,ProgressBar progressBar){
        this.moviePoster = moviePoster;
        this.progressBar = progressBar;
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        this.progressBar.setVisibility(View.VISIBLE);
        return MovieUtility.getMovieDetailPoster(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap moviePoster) {
        this.progressBar.setVisibility(View.INVISIBLE);
        this.moviePoster.setImageBitmap(moviePoster);
    }
}
