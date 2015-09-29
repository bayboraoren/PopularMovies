package com.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.popularmovies.utility.ActionBarUtility;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.tablet_container) != null) {

            ActionBarUtility.actionBarVisible(getSupportActionBar(), false);


            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tablet_container,
                                movieDetailFragment,
                                MovieDetailFragment.TAG_FRAGMENT)
                        .commit();
            }
        } else {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mobile_container, new MovieFragment())
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
