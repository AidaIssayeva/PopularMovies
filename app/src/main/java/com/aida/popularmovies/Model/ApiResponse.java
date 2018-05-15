package com.aida.popularmovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aida on 5/12/18.
 */

public class ApiResponse {

    @SerializedName("results")
    public ArrayList<Movie> movies;

    @SerializedName("page")
    public int page;
}
