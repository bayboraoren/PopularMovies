package com.popularmovies.service;

import com.popularmovies.domain.Movie;
import com.popularmovies.domain.Movies;
import com.popularmovies.domain.Reviews;
import com.popularmovies.domain.Trailers;

import retrofit.http.GET;
import retrofit.http.Path;
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


    @GET("/movie/{movieId}")
    Movie getMovieById(@Path("movieId") String movieId,
                        @Query(API_KEY) String apiKey);


    @GET("/movie/{movieId}/videos")
    Trailers getTrailers(@Path("movieId") String movieId,
                         @Query(API_KEY) String apiKey);

    @GET("/movie/{movieId}/reviews")
    Reviews getReviews(@Path("movieId") String movieId,
                       @Query(API_KEY) String apiKey);


}
