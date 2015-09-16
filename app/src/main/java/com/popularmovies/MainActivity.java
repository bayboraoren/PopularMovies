package com.popularmovies;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.popularmovies.utility.ActionBarUtility;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for tablet
        if (findViewById(R.id.container) != null) {

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();


            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,
                                movieDetailFragment,
                                MovieDetailFragment.TAG_FRAGMENT)
                        .commit();
            }
        } else {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MovieFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }


    }


    @Override
    public void onBackPressed() {

        ActionBarUtility.actionBarVisible(getSupportActionBar(), false);

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }


}
