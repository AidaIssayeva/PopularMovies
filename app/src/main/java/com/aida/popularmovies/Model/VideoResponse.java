package com.aida.popularmovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aida on 5/26/18.
 */

public class VideoResponse {
    @SerializedName("results")
    public ArrayList<Video> list;
}
