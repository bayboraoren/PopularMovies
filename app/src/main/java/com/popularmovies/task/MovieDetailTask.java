package com.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.component.MovieDetailAdapter;
import com.popularmovies.domain.MovieDetail;
import com.popularmovies.domain.Review;
import com.popularmovies.domain.Reviews;
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
    private LinearLayout reviews;

    public MovieDetailTask(Context context,ImageView moviePoster, ProgressBar progressBar,TwoWayView trailerView,LinearLayout reviews){
        this.context = context;
        this.moviePoster = moviePoster;
        this.progressBar = progressBar;
        this.trailerView = trailerView;
        this.reviews = reviews;
    }


    @Override
    protected MovieDetail doInBackground(String... params) {

        String movieId=params[0];
        String posterPath=params[1];

        MovieDetail movieDetail = new MovieDetail();

        //Movie Trailers
        Trailers trailers= MovieUtility.getTrailers(movieId);
        movieDetail.setTrailers(trailers);

        //Movie Poster
        movieDetail.setPosterBitmap(MovieUtility.getMovieDetailPoster(posterPath));

        //Movie Reviews
        Reviews reviews = MovieUtility.getReviews(movieId);
        movieDetail.setReviews(reviews);


        this.progressBar.setVisibility(View.VISIBLE);

        return movieDetail;
    }

    @Override
    protected void onPostExecute(MovieDetail movieDetail) {
        this.progressBar.setVisibility(View.INVISIBLE);
        this.moviePoster.setImageBitmap(movieDetail.getPosterBitmap());
        MovieDetailAdapter movieDetailAdapter = new MovieDetailAdapter(context,movieDetail.getTrailers().getResults());
        this.trailerView.setAdapter(movieDetailAdapter);

        for(Review review:movieDetail.getReviews().getResults()){
            View reviewView = LayoutInflater.from(context).inflate(R.layout.review_list_item, null);
            ((TextView)reviewView.findViewById(R.id.reviewer_name)).setText(review.getAuthor());
            ((TextView)reviewView.findViewById(R.id.reviewer_content)).setText(review.getContent());
            reviews.addView(reviewView);
        }


    }
}
