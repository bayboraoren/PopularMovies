package com.popularmovies.utility;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.popularmovies.R;

/**
 * Created by baybora on 8/30/15.
 */
public class ActionBarUtility {

    public static void actionBarVisible(ActionBar actionBar,boolean visible){
        actionBar.setDisplayHomeAsUpEnabled(visible);
        actionBar.setHomeButtonEnabled(visible);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }

}

