package com.popularmovies.utility;

import com.google.gson.Gson;
import com.popularmovies.domain.Movies;

/**
 * Created by bora on 25.08.2015.
 */
public class JsonUtility {

    public static Movies jsonToMovies(String json){
        Gson gson = new Gson();
        Movies movies= gson.fromJson(json,Movies.class);
        return movies;
    }

}
