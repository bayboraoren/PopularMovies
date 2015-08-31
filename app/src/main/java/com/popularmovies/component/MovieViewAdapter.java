package com.popularmovies.component;

/**
 * Created by bora on 27.08.2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.popularmovies.R;
import com.popularmovies.domain.Movie;
import com.popularmovies.domain.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

public final class MovieViewAdapter extends BaseAdapter {

    private static final String LOG_TAG = MovieViewAdapter.class.getSimpleName();
    private MovieImageView view;
    private final Context context;
    private List<Movie> urls = new ArrayList<>();

    public MovieViewAdapter(Context context) {
        this.context = context;
    }


    public void  addMovies(Movies movies){
        urls.clear();
        urls.addAll(movies.getResults());
        this.notifyDataSetChanged();
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {


        view = (MovieImageView) convertView;

        if (view == null) {
            view = new MovieImageView(context);
            view.setScaleType(CENTER_CROP);
        }

        // Get the image URL for the current position.
        Movie movie =  getItem(position);
        String url = "http://image.tmdb.org/t/p/w370/" + movie.getPosterPath();




        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.placeholder) //
                .fit()
                .error(R.drawable.error)
                .into(view);

        return view;
    }

    @Override public int getCount() {
        return urls.size();
    }

    @Override public Movie getItem(int position) {
        return urls.get(position);
    }

    private void setItem(int position,Bitmap bitmap){
        Movie movie = urls.get(position);
        movie.setMoviePoster(bitmap);
        urls.set(position,movie);
    }

    @Override public long getItemId(int position) {
        return position;
    }


}