package com.popularmovies.domain;

import android.graphics.Bitmap;

/**
 * Created by baybora on 9/8/15.
 */
public class MovieDetail {

    private Bitmap posterBitmap;
    private Trailers trailers;
    private Reviews reviews;

    public Bitmap getPosterBitmap() {
        return posterBitmap;
    }

    public void setPosterBitmap(Bitmap posterBitmap) {
        this.posterBitmap = posterBitmap;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailers trailers) {
        this.trailers = trailers;
    }


    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }
}
