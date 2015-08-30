package com.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.popularmovies.component.MovieScrollListener;
import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.domain.Movie;
import com.popularmovies.task.MobileTask;
import com.popularmovies.utility.ActionBarUtility;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {

    private MovieViewAdapter movieViewAdapter;
    private boolean isSettingsSelected=false;
    private GridView gv;
    private int position;

    public MovieFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if(savedInstanceState!=null) {
            position = savedInstanceState.getInt("position");
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sort_by) {
            //remember scroll position
            isSettingsSelected=true;

            Intent intent = new Intent(getActivity(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //remember scroll position
        if(movieViewAdapter==null) {
            movieViewAdapter = new MovieViewAdapter(getActivity());
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gv = (GridView) view.findViewById(R.id.grid_view);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                position = pos;

                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable(Movie.PARCEABLE_KEY, movieViewAdapter.getItem(pos));
                movieDetailFragment.setArguments(args);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, movieDetailFragment, MovieDetailFragment.TAG_FRAGMENT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        gv.setAdapter(movieViewAdapter);
        gv.setOnScrollListener(new MovieScrollListener(getActivity()));


        return view;
    }


    private void update() {

        MobileTask mobileTask = new MobileTask(getActivity(),gv,position);
        mobileTask.execute();

        if(isSettingsSelected){
            gv.smoothScrollToPosition(0);
            isSettingsSelected=false;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBarUtility.actionBarVisible(((ActionBarActivity) getActivity()).getSupportActionBar(), false);
        update();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", gv.getLastVisiblePosition());
    }
}
