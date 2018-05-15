package com.aida.popularmovies.ViewModel;

import android.databinding.BaseObservable;
import android.view.View;

import com.aida.popularmovies.network.ApiFactory;

/**
 * Created by aida on 5/12/18.
 */

public class MovieListViewModel extends BaseObservable {
    public boolean isLoaded = false;
    private ApiFactory apiFactory;

    public MovieListViewModel(ApiFactory.ResponseListener responseListener) {
        apiFactory = new ApiFactory(responseListener);
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

}
