package com.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.domain.Movies;
import com.popularmovies.utility.MovieUtility;

/**
 * Created by baybora on 8/27/15.
 */
public class MobileTask extends AsyncTask<Void,Void,Movies>{

    private Context context;
    private GridView gv;
    private int position;

    public MobileTask(Context context,GridView gv,int position){
        this.context = context;
        this.gv = gv;
        this.position = position;
    }


    @Override
    protected Movies doInBackground(Void... voids) {
        return MovieUtility.getMovies(context);
    }

    @Override
    protected void onPostExecute(Movies movies) {
        MovieViewAdapter movieViewAdapter = (MovieViewAdapter)gv.getAdapter();
        movieViewAdapter.addMovies(movies);
        gv.smoothScrollToPosition(position);
    }
}
