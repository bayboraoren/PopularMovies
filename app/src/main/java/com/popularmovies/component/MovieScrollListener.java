package com.popularmovies.component;

/**
 * Created by bora on 27.08.2015.
 */
import android.content.Context;
import android.widget.AbsListView;

import com.squareup.picasso.Picasso;

public class MovieScrollListener implements AbsListView.OnScrollListener {
    private final Context context;

    public MovieScrollListener(Context context) {
        this.context = context;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        final Picasso picasso = Picasso.with(context);
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            picasso.resumeTag(context);
        } else {
            picasso.pauseTag(context);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        // Do nothing.
    }
}