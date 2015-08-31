package com.popularmovies.component;

/**
 * Created by bora on 27.08.2015.
 */

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * An image view which always remains square with respect to its width.
 */
public final class MovieImageView extends ImageView {

    public MovieImageView(Context context) {
        super(context);
        init();
    }

    public MovieImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovieImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST));
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected boolean setFrame(int frameLeft, int frameTop, int frameRight, int frameBottom) {
        float frameWidth = frameRight - frameLeft;
        float frameHeight = frameBottom - frameTop;

        float originalImageWidth = (float)getDrawable().getIntrinsicWidth();
        float originalImageHeight = (float)getDrawable().getIntrinsicHeight();

        float usedScaleFactor = 1;

        if((originalImageWidth > frameWidth ) || (originalImageHeight > frameHeight )) {

            float fitHorizontallyScaleFactor = frameWidth/originalImageWidth;
            float fitVerticallyScaleFactor = frameHeight/originalImageHeight;

            usedScaleFactor = Math.max(fitHorizontallyScaleFactor, fitVerticallyScaleFactor);
        }

        float newImageWidth = originalImageWidth * usedScaleFactor;
        float newImageHeight = originalImageHeight * usedScaleFactor;

        Matrix matrix = getImageMatrix();
        matrix.setScale(usedScaleFactor, usedScaleFactor, 0, 0);
        matrix.postTranslate((frameWidth - newImageWidth) /2, frameHeight - newImageHeight);
        setImageMatrix(matrix);
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom);
    }

}