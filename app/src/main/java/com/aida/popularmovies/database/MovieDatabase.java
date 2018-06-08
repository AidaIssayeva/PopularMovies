package com.aida.popularmovies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aida.popularmovies.Model.Movie;

/**
 * Created by aida on 6/2/18.
 */

@Database(entities = {Movie.class},
          version = 1,
          exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
