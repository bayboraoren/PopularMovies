package com.popularmovies.service;

import com.popularmovies.domain.Movies;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by bora on 01.09.2015.
 */
public interface MovieRestService {

    String SORT_BY = "sort_by";
    String API_KEY = "api_key";

    @GET("/discover/movie")
    Movies getMovies(@Query(API_KEY) String apiKey,
                     @Query(SORT_BY) String sortOrder);


}
