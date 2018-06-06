package com.aida.popularmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.aida.popularmovies.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aida on 6/2/18.
 */
@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySingleMovie(Movie movie);

    @Query("SELECT*FROM Movie WHERE id =:id")
    Movie fetchOneFavoriteMoviebyMovieId(int id);


    @Query("SELECT*FROM Movie")
    List<Movie> fetchFavoriteMovies();

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
