package com.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

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
    private ProgressBar progressBar;

    public MobileTask(Context context,GridView gv,int position,ProgressBar progressBar){
        this.context = context;
        this.gv = gv;
        this.position = position;
        this.progressBar = progressBar;
    }


    @Override
    protected Movies doInBackground(Void... voids) {
        this.progressBar.setVisibility(View.VISIBLE);
        return MovieUtility.getMoviesWithRetrofit(context);
    }

    @Override
    protected void onPostExecute(Movies movies) {
        this.progressBar.setVisibility(View.INVISIBLE);
        MovieViewAdapter movieViewAdapter = (MovieViewAdapter)gv.getAdapter();
        movieViewAdapter.addMovies(movies);
        gv.setSelection(position);
    }
}
