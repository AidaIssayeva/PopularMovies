package com.aida.popularmovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/12/18.
 */

public class Utils {

    public static String API_KEY = "fce8bae86bd9073c55ab2d6a415f55c4";
    public static String BASE_URL = "http://api.themoviedb.org/3/movie/";
    public static String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    public static String W342 = "w342/";
    public static String W185 = "w185/";
    public static String W500 = "w500/";
    public static String TRAILERS = "Trailers";
    public static String REVIEWS = "Reviews";

    public static String INTENT_STRING = "movie";

    public static final String DATABASE_NAME = "movies_db";

    public static void setImage( ImageView view, String url){
        final Context context = view.getContext();
        Picasso.with(context)
                .load(url)
                .noFade()
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        ((AppCompatActivity) context).supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        ((AppCompatActivity) context).supportStartPostponedEnterTransition();
                    }
                });
    }
}
