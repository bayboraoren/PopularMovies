package com.popularmovies;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.popularmovies.domain.Movie;
import com.popularmovies.utility.ActionBarUtility;

/**
 * Created by baybora on 8/28/15.
 */
public class MovieDetailFragment extends Fragment {

    public final static String TAG_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ActionBarUtility.actionBarVisible(((ActionBarActivity) getActivity()).getSupportActionBar(), true);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Movie movie = getArguments().getParcelable(Movie.PARCEABLE_KEY);


        //Poster Image
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.movie_poster);
        imageView.setImageBitmap(movie.getMoviePoster());

        //Title
        TextView title = (TextView) view.findViewById(R.id.movie_title);
        title.setText(movie.getTitle());


        //Release Date
        TextView releaseDate = (TextView) view.findViewById(R.id.movie_release_date);
        releaseDate.setText("Release Date, " + movie.getReleaseDate());

        //Vote
        float rating = 5 * movie.getVoteAverage().floatValue() / 10;
        RatingBar voteRatingBar = (RatingBar) view.findViewById(R.id.movie_vote);
        voteRatingBar.setRating(rating);

        //RatingBar Color Black
        LayerDrawable stars = (LayerDrawable) voteRatingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);

        //Synopsis
        //Release Date
        TextView synopsis = (TextView) view.findViewById(R.id.movie_synopsis);
        synopsis.setText(movie.getOverview());


        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MovieFragment())
                        .commit();


                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }




}
