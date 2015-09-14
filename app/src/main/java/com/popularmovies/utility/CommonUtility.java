package com.popularmovies.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.popularmovies.R;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by baybora on 8/28/15.
 */
public class CommonUtility {

    public static String getPreferredSortBy(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_sort_by_key),
                context.getString(R.string.pref_sort_by_default));
    }


    public static Iterator<String> getFavoriteMovies(Context context){
        SharedPreferences prefs = context.getSharedPreferences(FavoriteUtility.FAVORITE,Context.MODE_PRIVATE);
        Map<String,String> movies = (Map<String, String>) prefs.getAll();
        return movies.keySet().iterator();

    }

}
