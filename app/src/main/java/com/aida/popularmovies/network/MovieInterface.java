package com.aida.popularmovies.network;

import com.aida.popularmovies.Model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aida on 5/12/18.
 */

public interface MovieInterface {
    @GET("popular")
    Call<ApiResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<ApiResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
