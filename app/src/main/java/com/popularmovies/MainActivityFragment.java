package com.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.popularmovies.component.MovieViewAdapter;
import com.popularmovies.component.MovieScrollListener;
import com.popularmovies.task.MobileTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        MovieViewAdapter movieViewAdapter = new MovieViewAdapter(getActivity());
        MobileTask mobileTask = new MobileTask(movieViewAdapter);
        mobileTask.execute();

        GridView gv = (GridView) view.findViewById(R.id.grid_view);
        gv.setAdapter(movieViewAdapter);
        gv.setOnScrollListener(new MovieScrollListener(getActivity()));





        /*Intent intent = new Intent(getActivity(), MovieService.class);
        getActivity().startService(intent);*/


        return view;
    }
}
