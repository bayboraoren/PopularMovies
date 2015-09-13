package com.popularmovies.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by baybora on 9/13/15.
 */
public class FavoriteUtility {

    private static final String FAVORITE = "favorite";

    public static void add(Context context,String movieId){
        SharedPreferences.Editor editor = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE).edit();
        editor.putString(movieId,movieId);
        editor.commit();
    }

    public static void remove(Context context,String movieId){
        SharedPreferences.Editor editor = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE).edit();
        editor.remove(movieId);
        editor.apply();
    }

    public static Iterator<String> getAll(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FAVORITE, Context.MODE_PRIVATE);
        Map<String,String> all = (Map<String, String>) sharedPreferences.getAll();
        return all.values().iterator();
    }


    public static boolean isExist(Context context,String movieId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FAVORITE,Context.MODE_PRIVATE);
        return sharedPreferences.contains(movieId);
    }

}
