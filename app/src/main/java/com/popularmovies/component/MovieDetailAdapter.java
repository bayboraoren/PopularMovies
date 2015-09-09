package com.popularmovies.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.popularmovies.R;
import com.popularmovies.domain.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by baybora on 9/8/15.
 */
public class MovieDetailAdapter extends ArrayAdapter<Trailer> {

    private Context context;

    public MovieDetailAdapter(Context context, ArrayList<Trailer> movieDetail) {
        super(context, 0, movieDetail);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        Trailer trailer = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list_item, parent, false);
        }

        MovieTrailerThumbnailImageView trailerThumbnail = (MovieTrailerThumbnailImageView)convertView.findViewById(R.id.movie_trailer);

        final String youtubeThumbnailUrl = String.format("http://img.youtube.com/vi/%s/mqdefault.jpg",trailer.getKey());

        Picasso.with(context) //
                .load(youtubeThumbnailUrl) //
                .placeholder(R.drawable.placeholder_400_200) //
                .into(trailerThumbnail);


        return convertView;
    }

}
