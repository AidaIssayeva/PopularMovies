package com.aida.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Model.MovieResponse;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Model.VideoResponse;
import com.aida.popularmovies.ViewModel.MovieListViewModel;
import com.aida.popularmovies.databinding.ActivityMainBinding;
import com.aida.popularmovies.network.ApiFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiFactory.ResponseListener {

    private MovieListViewModel movieViewModel;
    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.pink_gradient));


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieViewModel = new MovieListViewModel(this, this);
        activityMainBinding.setViewModel(movieViewModel);
        initRecyclerView(activityMainBinding.recyclerView);

        movieViewModel.gePopulartMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        movieViewModel.setLoaded(false);
        switch (item.getItemId()) {
            case R.id.topRated:
                movieViewModel.geTopRatedMovies();
                return true;
            case R.id.popular:
                movieViewModel.gePopulartMovies();
                return true;
            case R.id.favorite:
                movieViewModel.getFavoriteMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menu.getItem(0).setChecked(true);
        return true;
    }

    public void initRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void onSuccess(ApiResponse movieResponse) {
        recyclerView.setAdapter(new MovieAdapter(this,(ArrayList<Movie>) movieResponse.results));
        movieViewModel.setLoaded(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }


    public interface MovieHandler {
        void onMovieClick(View v, Movie movie);
    }
}
