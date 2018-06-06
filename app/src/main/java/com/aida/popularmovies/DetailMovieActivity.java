package com.aida.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aida.popularmovies.Model.ApiResponse;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Model.Review;
import com.aida.popularmovies.Model.Video;
import com.aida.popularmovies.ViewModel.MovieViewModel;
import com.aida.popularmovies.databinding.ActivityDetailBinding;
import com.aida.popularmovies.network.ApiFactory;

import java.util.ArrayList;


/**
 * Created by aida on 5/12/18.
 */
public class DetailMovieActivity extends AppCompatActivity implements ApiFactory.ResponseListener {


    private Movie movie;
    public MovieViewModel movieViewModel;
    ActivityDetailBinding detailMovieActivity;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private ArrayList<Object> trailers;
    private ArrayList<Object> reviews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getIntent() != null) {
            movie = getIntent().getParcelableExtra(Utils.INTENT_STRING);
        }
        movieViewModel = new MovieViewModel(this, this);
        supportPostponeEnterTransition();
        detailMovieActivity = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailMovieActivity.setModel(movie);
        detailMovieActivity.setViewModel(movieViewModel);
        movieViewModel.getTrailers(movie.id);
        movieViewModel.getReviews(movie.id);

    }

    @Override
    public void onSuccess(ApiResponse videoResponse) {
        //TODO: come up with cleaner solution
        if (!((ArrayList<Object>) videoResponse.results).isEmpty()) {
            if (((ArrayList<Object>) videoResponse.results).get(0) instanceof Video) {
                trailers = (ArrayList<Object>) videoResponse.results;
                detailMovieActivity.setVideo(((ArrayList<Video>) videoResponse.results).get(0));
            } else {
                reviews = (ArrayList<Object>) videoResponse.results;
            }
        }
        if (trailers != null && reviews != null) {
            expandableListViewAdapter = new ExpandableListViewAdapter(this, trailers, reviews);
            detailMovieActivity.trailersAndReviews.setAdapter(expandableListViewAdapter);
        }

    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public interface DetailHandler {
        void onTrailerClicked(View v, String key);
        void onLikeClicked(View v, Movie movie);
    }
}
