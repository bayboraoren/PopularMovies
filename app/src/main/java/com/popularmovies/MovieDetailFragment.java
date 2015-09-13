package com.popularmovies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.popularmovies.domain.Movie;
import com.popularmovies.domain.Trailer;
import com.popularmovies.task.MovieDetailTask;
import com.popularmovies.utility.ActionBarUtility;
import com.popularmovies.utility.FavoriteUtility;

import org.lucasr.twowayview.TwoWayView;

/**
 * Created by baybora on 8/28/15.
 */
public class MovieDetailFragment extends Fragment {

    public final static String TAG_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        final Movie movie = getArguments().getParcelable(Movie.PARCEABLE_KEY);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        //Progress Bar
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress);


        //Poster Image and Trailers
        ImageView imageView = (ImageView) view.findViewById(R.id.movie_poster);

        TwoWayView trailerView = (TwoWayView) view.findViewById(R.id.trailers);

        LinearLayout reviews = (LinearLayout) view.findViewById(R.id.reviews);

        MovieDetailTask movieDetailTask = new MovieDetailTask(getActivity(), imageView, progressBar, trailerView, reviews);
        movieDetailTask.execute(movie.getId().toString(), movie.getPosterPath());


        trailerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trailer trailer = ((Trailer) ((TwoWayView) parent).getAdapter().getItem(position));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey())));
            }
        });


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

        //Favorite
        final ImageView favorite = (ImageView) view.findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = movie.getId().toString();

                if(FavoriteUtility.isExist(getActivity(),movieId)){
                    FavoriteUtility.remove(getActivity(),movieId);
                    favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else{
                    FavoriteUtility.add(getActivity(), movieId);
                    favorite.setImageResource(R.drawable.ic_favorite_red_24dp);

                }
            }
        });

        if(FavoriteUtility.isExist(getActivity(),movie.getId().toString())){
            favorite.setImageResource(R.drawable.ic_favorite_red_24dp);
        }


        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    @Override
    public void onStart() {
        super.onStart();
        ActionBarUtility.actionBarVisible(((ActionBarActivity) getActivity()).getSupportActionBar(), true);
    }
}
