package com.popularmovies.component;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by baybora on 8/30/15.
 */
public class MoviePosterImageView extends ImageView {

    public MoviePosterImageView(Context context) {
        super(context);
        setScaleType(ScaleType.MATRIX);
    }


    public MoviePosterImageView(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
        setScaleType(ScaleType.MATRIX);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        recomputeImgMatrix();
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        recomputeImgMatrix();
        return super.setFrame(l, t, r, b);
    }

    private void recomputeImgMatrix() {
        final Matrix matrix = getImageMatrix();

        float scale;
        final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        final int drawableWidth = getDrawable().getIntrinsicWidth();
        final int drawableHeight = getDrawable().getIntrinsicHeight();

        if (drawableWidth * viewHeight > drawableHeight * viewWidth) {
            scale = (float) viewHeight / (float) drawableHeight;
        } else {
            scale = (float) viewWidth / (float) drawableWidth;
        }

        matrix.setScale(scale, scale);
        setImageMatrix(matrix);
    }

}
