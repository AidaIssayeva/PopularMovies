package com.aida.popularmovies.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.aida.popularmovies.Utils;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aida on 5/12/18.
 */
@Entity
public class Movie implements Parcelable {
    public Movie(){}
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("vote_average")
    public double voteAverage;

    @SerializedName("video")
    public boolean hasVideo;
    @SerializedName("title")
    public String title;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("overview")
    public String overView;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String language;


//    @SerializedName("genre_ids")
//    public ArrayList<Integer> genreIds;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("adult")
    public boolean adultsOnly;

    protected Movie(Parcel in) {
        voteCount = in.readInt();
        voteAverage = in.readDouble();
        hasVideo = in.readByte() != 0;
        title = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        popularity = in.readDouble();
        overView = in.readString();
        originalTitle = in.readString();
        language = in.readString();
        id = in.readInt();
        backdropPath = in.readString();
        adultsOnly = in.readByte() != 0;
    }

    public String getRatings() {
        return String.valueOf(voteAverage);
    }

    public String getRelease() {
        String formatteddate = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
            formatteddate = new SimpleDateFormat("YYYY MMMM dd").format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatteddate;
    }

    public String getThumbnailUrl() {
        return Utils.BASE_IMAGE_URL + Utils.W342 + posterPath;
    }

    public String getBackgroundUrl() {
        return Utils.BASE_IMAGE_URL + Utils.W500 + posterPath;
    }

    public String getOriginalLanguage() {
        String originalLanguage = language;
        for (Language languageEnum : Language.values()) {
            if (language.equalsIgnoreCase(languageEnum.name())) {
                originalLanguage = languageEnum.full;
            }
        }
        return originalLanguage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(voteCount);
        dest.writeDouble(voteAverage);
        dest.writeByte((byte) (hasVideo ? 1 : 0));
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterPath);
        dest.writeDouble(popularity);
        dest.writeString(overView);
        dest.writeString(originalTitle);
        dest.writeString(language);
        dest.writeInt(id);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adultsOnly ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
