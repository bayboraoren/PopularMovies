package com.popularmovies.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by baybora on 9/9/15.
 */
public class MovieTrailerThumbnailImageView extends ImageView {

    public MovieTrailerThumbnailImageView(Context context) {
        super(context);
    }

    public MovieTrailerThumbnailImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieTrailerThumbnailImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST));
    }



}
