package com.aida.popularmovies.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aida.popularmovies.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by aida on 5/12/18.
 */

public class MovieViewModel extends BaseObservable {

    public MovieViewModel() {}

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
}
