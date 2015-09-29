package com.popularmovies;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.popularmovies.domain.Movie;
import com.popularmovies.domain.Trailer;
import com.popularmovies.event.FavoriteEvent;
import com.popularmovies.task.MovieDetailTask;
import com.popularmovies.utility.CommonUtility;
import com.popularmovies.utility.FavoriteUtility;

import org.lucasr.twowayview.TwoWayView;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by baybora on 8/28/15.
 */
public class MovieDetailFragment extends Fragment {

    public final static String TAG_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";
    private final static String YOUTUBE_URL = "http://www.youtube.com/watch?v=";

    @Bind(R.id.progress)
    ProgressBar progressBar;
    @Bind(R.id.movie_poster)
    ImageView imageView;
    @Bind(R.id.trailers)
    TwoWayView trailerView;
    @Bind(R.id.reviews)
    LinearLayout reviews;
    @Bind(R.id.movie_title)
    TextView title;
    @Bind(R.id.movie_release_date)
    TextView releaseDate;
    @Bind(R.id.movie_vote)
    AppCompatRatingBar voteRatingBar;
    @Bind(R.id.movie_synopsis)
    TextView synopsis;
    @Bind(R.id.favorite)
    ImageView favorite;
    @BindDrawable(R.drawable.favorite_icon)
    Drawable favoriteIcon;
    @BindDrawable(R.drawable.favorite_icon_selected)
    Drawable favoriteIconSelected;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);

        if (getArguments() != null) {


            //action bar visibility for tablet and mobile
            boolean actionBarVisibility = !getArguments().getBoolean(MovieFragment.IS_TABLET);
            CommonUtility.actionBarVisible(getActivity(),actionBarVisibility);

            final Movie movie = getArguments().getParcelable(Movie.PARCEABLE_KEY);

            MovieDetailTask movieDetailTask = new MovieDetailTask(getActivity(),imageView,progressBar, trailerView, reviews);
            movieDetailTask.execute(movie.getId().toString(), movie.getPosterPath());


            trailerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Trailer trailer = ((Trailer) ((TwoWayView) parent).getAdapter().getItem(position));
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_URL + trailer.getKey())));
                }
            });

            //title
            title.setText(movie.getTitle());

            //Release Date
            releaseDate.setText("Release Date, " + movie.getReleaseDate());

            //Vote
            float rating = 5 * movie.getVoteAverage().floatValue() / 10;
            voteRatingBar.setRating(rating);

            //Synopsis
            synopsis.setText(movie.getOverview());

            //Favorite
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String movieId = movie.getId().toString();

                    if (FavoriteUtility.isExist(getActivity(), movieId)) {
                        FavoriteUtility.remove(getActivity(), movieId);
                        favorite.setImageDrawable(favoriteIcon);

                    } else {
                        FavoriteUtility.add(getActivity(), movieId);
                        favorite.setImageDrawable(favoriteIconSelected);
                    }

                    EventBus.getDefault().post(new FavoriteEvent());

                }
            });

            if (FavoriteUtility.isExist(getActivity(), movie.getId().toString())) {
                favorite.setImageDrawable(favoriteIconSelected);
            }

        }else{
            //don't show detail if you didn't choose movie
            container.setVisibility(View.GONE);
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
    }
}
