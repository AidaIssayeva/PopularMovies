package com.aida.popularmovies.network;

import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aida.popularmovies.Utils.BASE_URL;

/**
 * Created by aida on 5/12/18.
 */

public class ApiFactory implements Callback<ApiResponse> {
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
        Call<ApiResponse> call = movieApi.getPopularMovies(Utils.API_KEY);
        call.enqueue(this);

    }

    public void getTopRatedMovies() {
        Call<ApiResponse> call = movieApi.getTopRatedMovies(Utils.API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        if (response.body() != null) {
            responseListener.onSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        responseListener.onError(t);
    }

    public interface ResponseListener {
        void onSuccess(ApiResponse apiResponse);

        void onError(Throwable t);
    }
}
