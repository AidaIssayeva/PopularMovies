package com.aida.popularmovies.network;

import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Model.MovieResponse;
import com.aida.popularmovies.Model.Review;
import com.aida.popularmovies.Model.Video;
import com.aida.popularmovies.Model.VideoResponse;
import com.aida.popularmovies.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aida.popularmovies.Utils.BASE_URL;

/**
 * Created by aida on 5/12/18.
 */

public class ApiFactory<T extends ApiResponse> implements Callback<ApiResponse<T>> {
    private static Retrofit retrofit = null;
    private MovieInterface movieApi;

    private ResponseListener responseListener;

    public ApiFactory(ResponseListener responseListener) {
        this.responseListener = responseListener;
        movieApi = getClient().create(MovieInterface.class);
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public void getPopularMovies() {
        Call<ApiResponse<ArrayList<Movie>>> call = movieApi.getPopularMovies(Utils.API_KEY);
        call.enqueue(new Callback<ApiResponse<ArrayList<Movie>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ArrayList<Movie>>> call, Response<ApiResponse<ArrayList<Movie>>> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ArrayList<Movie>>> call, Throwable t) {
                responseListener.onError(t);
            }
        });

    }

    public void getTopRatedMovies() {
        Call<ApiResponse<ArrayList<Movie>>> call = movieApi.getTopRatedMovies(Utils.API_KEY);
        call.enqueue(new Callback<ApiResponse<ArrayList<Movie>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ArrayList<Movie>>> call, Response<ApiResponse<ArrayList<Movie>>> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ArrayList<Movie>>> call, Throwable t) {
                responseListener.onError(t);
            }
        });
    }

    public void getTrailers(int id) {
        Call<ApiResponse<ArrayList<Video>>> call = movieApi.getTrailers(id, Utils.API_KEY);
        call.enqueue(new Callback<ApiResponse<ArrayList<Video>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ArrayList<Video>>> call, Response<ApiResponse<ArrayList<Video>>> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ArrayList<Video>>> call, Throwable t) {
                responseListener.onError(t);
            }
        });
    }

    public void getReviews(int id) {
        Call<ApiResponse<ArrayList<Review>>> call = movieApi.getReviews(id, Utils.API_KEY);
        call.enqueue(new Callback<ApiResponse<ArrayList<Review>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ArrayList<Review>>> call, Response<ApiResponse<ArrayList<Review>>> response) {
                if (response.body() != null) {
                    responseListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ArrayList<Review>>> call, Throwable t) {
                responseListener.onError(t);
            }
        });
    }


    @Override
    public void onFailure(Call call, Throwable t) {
        //responseListener.onError(t);
    }

    @Override
    public void onResponse(Call call, Response response) {
//        if (response.body() != null) {
//            responseListener.onSuccess((ApiResponse) response.body());
//        }
    }

    public interface ResponseListener {
        void onSuccess(ApiResponse response);
        void onError(Throwable t);
    }
}
