package com.aida.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.ViewModel.MovieViewModel;
import com.aida.popularmovies.databinding.ActivityDetailBinding;


/**
 * Created by aida on 5/12/18.
 */
public class DetailMovieActivity extends AppCompatActivity {


    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getIntent() != null) {
            movie = getIntent().getParcelableExtra(Utils.INTENT_STRING);
        }
        supportPostponeEnterTransition();
        ActivityDetailBinding detailMovieActivity = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailMovieActivity.setModel(movie);
        detailMovieActivity.setViewModel(new MovieViewModel());

    }
}
