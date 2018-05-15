package com.aida.popularmovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aida.popularmovies.Model.Movie;
import com.aida.popularmovies.ViewModel.MovieItemViewModel;
import com.aida.popularmovies.databinding.ViewMovieBinding;

import java.util.ArrayList;

/**
 * Created by aida on 5/12/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewMovieBinding itemBinding =
                ViewMovieBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        Movie movie = getItemForPosition(position);
        holder.bind(movie, context);
    }

    public Movie getItemForPosition(int position) {
        return movies.get(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ViewMovieBinding binding;

        public MovieViewHolder(ViewMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie, Context context) {
            binding.setModel(movie);
            binding.setViewModel(new MovieItemViewModel((AppCompatActivity) context));
            binding.executePendingBindings();
        }
    }
}


