package com.aida.popularmovies.network;

import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Model.MovieResponse;
import com.aida.popularmovies.Model.Review;
import com.aida.popularmovies.Model.Video;
import com.aida.popularmovies.Model.VideoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aida on 5/12/18.
 */

public interface MovieInterface {
    @GET("popular")
    Call<ApiResponse<ArrayList<Movie>>> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<ApiResponse<ArrayList<Movie>>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("{id}/videos")
    Call<ApiResponse<ArrayList<Video>>> getTrailers(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("{id}/reviews")
    Call<ApiResponse<ArrayList<Review>>> getReviews(@Path("id") int movieId, @Query("api_key") String apiKey);

}
