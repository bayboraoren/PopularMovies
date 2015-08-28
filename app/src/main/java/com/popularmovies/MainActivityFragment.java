package com.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.popularmovies.component.MovieScrollListener;
import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.task.MobileTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MovieViewAdapter movieViewAdapter;



    public MainActivityFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        movieViewAdapter = new MovieViewAdapter(getActivity());

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        GridView gv = (GridView) view.findViewById(R.id.grid_view);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new MovieDetailFragment(),MovieDetailFragment.TAG_FRAGMENT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        gv.setAdapter(movieViewAdapter);
        gv.setOnScrollListener(new MovieScrollListener(getActivity()));

        return view;
    }


    private void update() {
        MobileTask mobileTask = new MobileTask(movieViewAdapter, getActivity());
        mobileTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        update();
    }



}
