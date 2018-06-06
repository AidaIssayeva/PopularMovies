package com.aida.popularmovies.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aida on 5/26/18.
 */

public class Review  {
    @SerializedName("author")
    public String author;

    @SerializedName("content")
    public String content;

    @SerializedName("url")
    public String url;

    @SerializedName("id")
    public String id;
}
