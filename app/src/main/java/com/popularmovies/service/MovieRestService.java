package com.popularmovies.service;

import com.popularmovies.domain.Movies;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * Created by bora on 01.09.2015.
 */
public interface MovieRestService {

    String SORT_BY = "sort_by";
    String API_KEY = "api_key";


    @GET("/discover/movie?api_key=c8d8be2cdf2ddd5cb31cf3a04460210c")
    Movies popular(@Query("sortby") String sortOrder, Callback<Movies> callback);

    @GET("/discover/movie")
    Movies topRated(@Query(API_KEY) String apiKey,@Query(SORT_BY) String sortBy);

}
