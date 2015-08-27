package com.popularmovies.component;

/**
 * Created by bora on 27.08.2015.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/** An image view which always remains square with respect to its width. */
public final class MovieImageView extends ImageView {
    public MovieImageView(Context context) {
        super(context);
    }

    public MovieImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST));
    }


}