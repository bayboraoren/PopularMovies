package com.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.popularmovies.component.MovieScrollListener;
import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.domain.Movie;
import com.popularmovies.event.FavoriteEvent;
import com.popularmovies.task.MobileTask;
import com.popularmovies.utility.CommonUtility;
import com.popularmovies.utility.FavoriteUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {

    private MovieViewAdapter movieViewAdapter;
    private boolean isSettingsSelected = false;
    private int position;
    private final static String POSITION = "position";
    private final static String MOVIES = "movies";
    public final static String IS_TABLET = "isTablet";
    private boolean isTablet;

    @Bind(R.id.grid_view)
    GridView gv;
    @Bind(R.id.progress)
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (movieViewAdapter == null) {
            movieViewAdapter = new MovieViewAdapter(getActivity());
        }

        if (savedInstanceState != null) {

            List<Movie> movieList = savedInstanceState.getParcelableArrayList(MOVIES);
            //Add movie list directly on movie view adapter
            movieViewAdapter.addMovieList(movieList);

        }

    }

    public void onEvent(FavoriteEvent event) {
        //tablet and selected favorite
        if (isTablet && CommonUtility.getPreferredSortBy(getActivity()).equals(FavoriteUtility.FAVORITE)) {
            updateByFavorite();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sort_by) {
            //remember scroll position
            isSettingsSelected = true;

            //start activity with result to get sort by changed in settings activity
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                //remember scroll position
                position = pos;

                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable(Movie.PARCEABLE_KEY, movieViewAdapter.getItem(pos));


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                if (getActivity().findViewById(R.id.tablet_container) != null) {

                    isTablet = true;
                    args.putBoolean(IS_TABLET, isTablet);
                    movieDetailFragment.setArguments(args);
                    fragmentTransaction.replace(R.id.tablet_container, movieDetailFragment, MovieDetailFragment.TAG_FRAGMENT);
                    getActivity().findViewById(R.id.tablet_container).setVisibility(View.VISIBLE);

                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 2) {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }

                } else {

                    isTablet = false;
                    args.putBoolean(IS_TABLET, isTablet);
                    movieDetailFragment.setArguments(args);
                    fragmentTransaction.replace(R.id.mobile_container, movieDetailFragment, MovieDetailFragment.TAG_FRAGMENT);

                }


                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        gv.setAdapter(movieViewAdapter);
        gv.setOnScrollListener(new MovieScrollListener(getActivity()));


        return view;
    }


    private void update() {

        if (isNetworkConnected()) {

            MobileTask mobileTask = new MobileTask(getActivity(), gv, position, progressBar);
            mobileTask.execute(false);

            if (isSettingsSelected) {
                gv.setSelection(0);
                isSettingsSelected = false;
            }
        }

    }


    private void updateByFavorite() {

        if (isNetworkConnected()) {

            MobileTask mobileTask = new MobileTask(getActivity(), gv, position, progressBar);
            //is favorite true or false
            mobileTask.execute(true);

            if (isSettingsSelected) {
                gv.setSelection(0);
                isSettingsSelected = false;
            }
        }

    }


    private boolean isNetworkConnected() {
        if (CommonUtility.isNetworkConnected(getActivity())) {
            return true;
        } else {
            String message = getActivity().getString(R.string.network_not_available);
            CommonUtility.showToast(getActivity(), message);
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
        CommonUtility.actionBarVisible(getActivity(), false);

        if (CommonUtility.getPreferredSortBy(getActivity()).equals(FavoriteUtility.FAVORITE)) {
            updateByFavorite();
        } else if (movieViewAdapter.getItems().size() == 0) { //get movie list first time
            update();
        }
    }


    //get sort by changing in settings activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            if (data.getExtras().getBoolean(SettingsActivity.SORT_BY_CHANGED)) {

                String sortBy = CommonUtility.getPreferredSortBy(getActivity());
                if (sortBy.equals(getString(R.string.pref_sort_by_favorite))) {
                    updateByFavorite();
                } else {
                    update();
                }

            }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        //remember scroll position
        if (gv != null) {
            position = gv.getFirstVisiblePosition();
        }

        int orientation = getResources().getConfiguration().orientation;

        switch (orientation) {
            case Surface.ROTATION_0: //portraid
                position -= 5;
                break;
            case Surface.ROTATION_90: //landscape
                position -= 2;
                break;
            case Surface.ROTATION_180: //reverse portraid
                position += 2;
                break;
            default://reverse landscape
                position += 5;
                break;
        }

        //Movie List
        ArrayList<Movie> movies = (ArrayList) movieViewAdapter.getItems();

        outState.putInt(POSITION, position);
        outState.putParcelableArrayList(MOVIES, movies);


    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
