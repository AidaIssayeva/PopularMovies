package com.aida.popularmovies.ViewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.aida.popularmovies.DetailMovieActivity;
import com.aida.popularmovies.MainActivity;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/13/18.
 */

public class MovieItemViewModel extends BaseObservable implements MainActivity.MovieHandler {
    AppCompatActivity activity;

    public MovieItemViewModel(AppCompatActivity activity) {
        this.activity = activity;
    }

    @BindingAdapter("imageUrl")
    public static void setPoster(final ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }

    @Override
    public void onMovieClick(View v, Movie movie) {
        Intent intent = new Intent(activity, DetailMovieActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity,
                        v,
                        ViewCompat.getTransitionName(v));
        intent.putExtra(Utils.INTENT_STRING, movie);
        activity.startActivity(intent, options.toBundle());
    }
}
