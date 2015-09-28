package com.popularmovies.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

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


    public static Iterator<String> getFavoriteMovies(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(FavoriteUtility.FAVORITE, Context.MODE_PRIVATE);
        Map<String, String> movies = (Map<String, String>) prefs.getAll();
        return movies.keySet().iterator();

    }


    public static void actionBarVisible(Context context, boolean visible) {
        ActionBarUtility.actionBarVisible(((ActionBarActivity) context).getSupportActionBar(), visible);
    }

    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void showAlert(Context context,String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(context.getString(R.string.alert));
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    public static void showToast(Context context,String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

}
