package com.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.popularmovies.component.MovieDetailAdapter;
import com.popularmovies.domain.MovieDetail;
import com.popularmovies.domain.Trailers;
import com.popularmovies.utility.MovieUtility;

import org.lucasr.twowayview.TwoWayView;

/**
 * Created by baybora on 8/30/15.
 */
public class MovieDetailTask extends AsyncTask<String,Integer,MovieDetail> {

    private Context context;
    private TwoWayView trailerView;
    private ImageView moviePoster;
    private ProgressBar progressBar;

    public MovieDetailTask(Context context,ImageView moviePoster, ProgressBar progressBar,TwoWayView trailerView){
        this.context = context;
        this.moviePoster = moviePoster;
        this.progressBar = progressBar;
        this.trailerView = trailerView;
    }


    @Override
    protected MovieDetail doInBackground(String... params) {

        String movieId=params[0];
        String posterPath=params[1];

        MovieDetail movieDetail = new MovieDetail();
        Trailers trailers= MovieUtility.getTrailers(movieId);

        movieDetail.setTrailers(trailers);

        movieDetail.setPosterBitmap(MovieUtility.getMovieDetailPoster(posterPath));

        this.progressBar.setVisibility(View.VISIBLE);

        return movieDetail;
    }

    @Override
    protected void onPostExecute(MovieDetail movieDetail) {
        this.progressBar.setVisibility(View.INVISIBLE);
        this.moviePoster.setImageBitmap(movieDetail.getPosterBitmap());
        MovieDetailAdapter movieDetailAdapter = new MovieDetailAdapter(context,movieDetail.getTrailers().getResults());
        this.trailerView.setAdapter(movieDetailAdapter);
    }
}
