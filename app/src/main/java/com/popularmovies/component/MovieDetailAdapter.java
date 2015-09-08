package com.popularmovies.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.domain.MovieDetail;
import com.popularmovies.domain.Trailer;

import java.util.ArrayList;

/**
 * Created by baybora on 9/8/15.
 */
public class MovieDetailAdapter extends ArrayAdapter<Trailer> {

    public MovieDetailAdapter(Context context, ArrayList<Trailer> movieDetail) {
        super(context, 0, movieDetail);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Trailer trailer = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list_item, parent, false);
        }

        ImageView trailerThumbnail = (ImageView)convertView.findViewById(R.id.trailer_thumbnail);
        trailerThumbnail.setImageResource(R.drawable.movie_trailer_thumbnail);



        TextView trailerTitle = (TextView)convertView.findViewById(R.id.trailer_title);
        trailerTitle.setText(trailer.getName());

        return convertView;
    }

}
