package com.aida.popularmovies.ViewModel;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aida.popularmovies.AppExecutors;
import com.aida.popularmovies.DetailMovieActivity;
import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.Utils;
import com.aida.popularmovies.database.MovieDatabase;
import com.aida.popularmovies.network.ApiFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by aida on 5/12/18.
 */

public class MovieViewModel extends BaseObservable implements DetailMovieActivity.DetailHandler {
    private ApiFactory apiFactory;
    private static MovieDatabase movieDatabase;

    private static boolean isFav = false;


    public MovieViewModel(Context context, ApiFactory.ResponseListener responseListener) {
        apiFactory = new ApiFactory(responseListener);
        movieDatabase = Room.databaseBuilder(context.getApplicationContext(),
                MovieDatabase.class, Utils.DATABASE_NAME)
                .build();
    }

    @BindingAdapter("backgroundUrl")
    public static void setBackgroundImage(final ImageView view, String backgroundUrl) {
        Utils.setImage(view, backgroundUrl);
    }

    @BindingAdapter("backgroundUrl")
    public static void setBackgroundColor(final ViewGroup viewGroup, String backgroundUrl) {
        Context context = viewGroup.getContext();
        Picasso.with(context)
                .load(backgroundUrl)
                .noFade()
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        Palette.Swatch textSwatch = palette.getDarkMutedSwatch();
                                        if (textSwatch == null) {
                                            return;
                                        }
                                        viewGroup.setBackgroundColor(textSwatch.getRgb());
                                    }
                                });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {}
                });

    }

    @BindingAdapter("thumbUrl")
    public static void setThumbnailImage(final ImageView view, String thumbnailUrl) {
        Utils.setImage(view, thumbnailUrl);
    }

    @BindingAdapter("isFavorite")
    public static void isFavorite(final View btn, final Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Movie movie1 = movieDatabase.daoAccess().fetchOneFavoriteMoviebyMovieId(movie.id);
                if (movie1 != null) {
                    btn.setPressed(true);
                    isFav = true;
                }
            }
        });

    }

    @Override
    public void onTrailerClicked(View v, String key) {
        if (key != null) {
            openYoutube(v.getContext(), key);
        }
    }

    @Override
    public void onLikeClicked(final View v, final Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!isFav) {
                    movieDatabase.daoAccess().insertOnlySingleMovie(movie);
                    v.setPressed(true);
                    isFav = true;
                } else {
                    movieDatabase.daoAccess().deleteMovie(movie);
                    v.setPressed(false);
                    isFav = false;
                }
            }
        });


    }

    public void getTrailers(int id) {
        apiFactory.getTrailers(id);
    }

    public void getReviews(int id) {
        apiFactory.getReviews(id);
    }

    public void openYoutube(Context context, String key) {
        Uri uri = Uri.parse("https://www.youtube.com/watch?v=" + key);
        uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


}
