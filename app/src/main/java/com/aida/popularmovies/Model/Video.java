package com.aida.popularmovies.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aida on 5/26/18.
 */

public class Video {
    @SerializedName("id")
    public String id;

    @SerializedName("key")
    public String key;

    @SerializedName("site")
    public String site;

    @SerializedName("type")
    public String type;

}
