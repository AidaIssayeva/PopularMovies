package com.aida.popularmovies.ViewModel;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.aida.popularmovies.AppExecutors;
import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.R;
import com.aida.popularmovies.Utils;
import com.aida.popularmovies.database.MovieDatabase;
import com.aida.popularmovies.network.ApiFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aida on 5/12/18.
 */

public class MovieListViewModel extends BaseObservable {
    public boolean isLoaded = false;
    private ApiFactory<ApiResponse<Movie>> apiFactory;
    private static MovieDatabase movieDatabase;
    ApiFactory.ResponseListener responseListener;
    Context context;

    public MovieListViewModel(Context context, ApiFactory.ResponseListener responseListener) {
        this.context = context;
        this.responseListener = responseListener;
        apiFactory = new ApiFactory(responseListener);
        movieDatabase = Room.databaseBuilder(context.getApplicationContext(),
                MovieDatabase.class, Utils.DATABASE_NAME)
                .build();
    }

    public void gePopulartMovies() {
        apiFactory.getPopularMovies();
    }

    public void geTopRatedMovies() {
        apiFactory.getTopRatedMovies();
    }

    public void setLoaded(boolean loaded) {
        this.isLoaded = loaded;
        notifyChange();
    }

    public int getRecyclerviewVisibility() {
        return isLoaded ? View.VISIBLE : View.INVISIBLE;
    }
    public void getFavoriteMovies(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = movieDatabase.daoAccess().fetchFavoriteMovies();
                if(movies != null){
                    final ApiResponse apiResponse = new ApiResponse();
                    apiResponse.results = movies;
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.onSuccess(apiResponse);
                        }
                    });

                }else{
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Throwable t = new Throwable(context.getString(R.string.message_no_favorite));
                            responseListener.onError(t);
                        }
                    });
                }
            }
        });
    }

}
