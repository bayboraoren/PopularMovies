package com.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.popularmovies.domain.Movie;

/**
 * Created by baybora on 8/28/15.
 */
public class MovieDetailFragment extends Fragment{

    public final static String TAG_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Movie movie = getArguments().getParcelable(Movie.PARCEABLE_KEY);

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.movie_poster);
        imageView.setImageBitmap(movie.getMoviePoster());


        return view;
    }

    



}
